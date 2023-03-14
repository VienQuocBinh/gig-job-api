package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.entity.Job;
import gigjob.model.domain.SearchCriteria;
import gigjob.model.request.JobRequest;
import gigjob.model.response.JobDetailResponse;
import gigjob.model.response.JobResponse;
import gigjob.repository.JobRepository;
import gigjob.repository.specification.JobSpecification;
import gigjob.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        Job job = jobRepository.save(modelMapper.map(jobRequest, Job.class));
        // add the job to Redis cache if not exist
        JobDetailResponse jobDetailResponse = modelMapper.map(job, JobDetailResponse.class);
//        redisTemplate.opsForHash().putIfAbsent(KEY, job.getId(), jobDetailResponse);
        return modelMapper.map(job, JobResponse.class);
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
    public List<JobDetailResponse> searchJob(SearchCriteria searchCriteria, int pageIndex, int pageSize) {
        try {
            Pageable pageable;
            if (searchCriteria.getSortCriteria().getDirection().equalsIgnoreCase("asc")) {
                pageable = PageRequest.of(pageIndex, pageSize, Sort.by(searchCriteria.getSortCriteria().getSortKey()).ascending());
            } else {
                pageable = PageRequest.of(pageIndex, pageSize, Sort.by(searchCriteria.getSortCriteria().getSortKey()).descending());
            }
            // Find job by job type specification
            Page<Job> jobs = jobRepository.findAll(JobSpecification.isOfficialJob(Integer.parseInt(searchCriteria.getValue()))
                            .and(JobSpecification.searchByTitle(searchCriteria.getSearchKey()))
                    , pageable);
            return jobs.stream()
                    .map(job -> modelMapper.map(job, JobDetailResponse.class))
                    .toList();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
