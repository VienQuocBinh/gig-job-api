package gigjob.service;

import gigjob.model.request.JobRequest;
import gigjob.model.response.JobResponse;

import java.util.List;

public interface JobService {
    JobResponse addJob(JobRequest jobRequest);

    List<JobResponse> getJobListRedis();

    List<JobResponse> getJob();

    JobResponse getJobById(Long id);

    JobResponse updateJob(JobRequest jobRequest);
}
