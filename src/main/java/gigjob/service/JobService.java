package gigjob.service;

import gigjob.entity.Job;
import gigjob.model.request.JobRequest;
import gigjob.model.response.JobDetailResponse;
import gigjob.model.response.JobResponse;

import java.util.List;
import java.util.Optional;

public interface JobService {
    JobResponse addJob(JobRequest jobRequest);

//    List<JobResponse> getJobListRedis();

    List<JobResponse> getJob();

    JobDetailResponse getJobById(Long id);

    JobResponse updateJob(JobRequest jobRequest);

    String deleteJob(Long id);

    Optional<Job> findJobById(Long id);
}
