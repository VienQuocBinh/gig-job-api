package gigjob.service.impl;

import gigjob.entity.Job;
import gigjob.model.request.JobRequest;
import gigjob.model.response.JobDetailResponse;
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
    private static final String KEY = "jobs";
    private static final String REDIS_KEY = "jobs::SimpleKey []";
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final RedisTemplate<String, List<JobDetailResponse>> redisTemplate;

    @Override
    public JobResponse addJob(JobRequest jobRequest) {
        Job job = jobRepository.save(modelMapper.map(jobRequest, Job.class));
        // add the job to Redis cache if not exist
        redisTemplate.opsForHash().putIfAbsent(KEY, job.getId(), job);
        return modelMapper.map(job, JobResponse.class);
    }

//    @Override
//    public List<JobResponse> getJobListRedis() {
//        return redisTemplate.opsForValue().get(REDIS_KEY);
//    }

    /**
     * The first time get Job from Database at  cached in Redis with the key {@code jobs::SimpleKey [] }
     * next time you call the method with the same arguments, the cached list of users will be returned directly from Redis cache without hitting the database.
     *
     * @return {@code  List<JobResponse>}
     * @author Vien Binh
     */
    @Override
    public List<JobDetailResponse> getJob() {
        List<JobDetailResponse> result;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(KEY))) {
            result = redisTemplate.opsForValue().get(KEY);
        } else {
            result = jobRepository.findAll().stream()
                    .map(job -> modelMapper.map(job, JobDetailResponse.class))
                    .toList();
            redisTemplate.opsForValue().set(KEY, result);
        }
        return result;
    }

    @Override
    public JobDetailResponse getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Job not found " + id));
        return modelMapper.map(job, JobDetailResponse.class);
    }

    @Override
    public JobResponse updateJob(JobRequest jobRequest) {
        Job oldJob = jobRepository.findById(jobRequest.getId()).orElseThrow(() -> new NotFoundException("Job not found " + jobRequest.getId()));
        Job requestJob = modelMapper.map(jobRequest, Job.class);
        // Set created date for updated job, if not it will be null
        requestJob.setCreatedDate(oldJob.getCreatedDate());
        redisTemplate.opsForHash().put(KEY, jobRequest.getId(), requestJob);
        return modelMapper.map(jobRepository.save(requestJob), JobResponse.class);
    }

    @Override
    public String deleteJob(Long id) {
        jobRepository.deleteById(id);
        redisTemplate.opsForHash().delete(KEY, id);
        return "Delete Job " + id + " successfully";
    }
}
