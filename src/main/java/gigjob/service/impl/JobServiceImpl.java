package gigjob.service.impl;

import gigjob.entity.Job;
import gigjob.model.request.JobRequest;
import gigjob.model.response.JobResponse;
import gigjob.repository.JobRepository;
import gigjob.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private static final String KEY = "Job";
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public JobResponse addJob(JobRequest jobRequest) {
        Job job = jobRepository.save(modelMapper.map(jobRequest, Job.class));
        redisTemplate.opsForHash().putIfAbsent(KEY, job.getId(), job);
        return modelMapper.map(job, JobResponse.class);
    }

    @Override
    public List<JobResponse> getJobListRedis() {
        List<Object> jobs = redisTemplate.opsForHash().values(KEY);
        return jobs.stream().map(job -> modelMapper.map(job, JobResponse.class)).toList();
    }

    @Override
    public List<JobResponse> getJob() {
        System.out.println("Get from db");
        return jobRepository.findAll()
                .stream()
                .map(job ->
                {
                    redisTemplate.opsForHash().putIfAbsent(KEY, job.getId(), job);
                    return modelMapper.map(job, JobResponse.class);
                }).toList();
    }

    @Override
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Job not found"));
        return modelMapper.map(job, JobResponse.class);
    }

    @Override
    public JobResponse updateJob(JobRequest jobRequest) {
        Job oldJob = jobRepository.findById(jobRequest.getId()).orElseThrow(() -> new NotFoundException("Job not found"));
        Job requestJob = modelMapper.map(jobRequest, Job.class);
        // Set created date for updated job, if not it will be null
        requestJob.setCreatedDate(oldJob.getCreatedDate());
        return modelMapper.map(jobRepository.save(requestJob), JobResponse.class);
    }
}
