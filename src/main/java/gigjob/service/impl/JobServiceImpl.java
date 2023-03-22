package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.entity.Job;
import gigjob.model.request.JobRequest;
import gigjob.model.response.JobDetailResponse;
import gigjob.model.response.JobResponse;
import gigjob.repository.JobRepository;
import gigjob.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

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
        try {
            Job j = modelMapper.map(jobRequest, Job.class);
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 14);
            dt = c.getTime();
            j.setExpiredDate(dt);
            Job job = jobRepository.save(j);
            // add the job to Redis cache if not exist
            JobDetailResponse jobDetailResponse = modelMapper.map(job, JobDetailResponse.class);
            redisTemplate.opsForHash().putIfAbsent(KEY, job.getId(), jobDetailResponse);
            return modelMapper.map(job, JobResponse.class);
        } catch (Exception exception) {
            throw new InternalServerErrorException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
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

    @Override
    public Optional<Job> findJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<JobDetailResponse> findJobsByShopId(UUID id) {
        var jobs = jobRepository.findAllByShopId(id);
        return jobs.stream().map(j -> modelMapper.map(j, JobDetailResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Page<Job> findBySearchCriteria(Specification<Job> specification, Pageable pageable) {
        try {

            return jobRepository.findAll(specification, pageable);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
