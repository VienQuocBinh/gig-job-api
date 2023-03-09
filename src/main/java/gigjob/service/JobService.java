package gigjob.service;

import gigjob.model.request.JobRequest;
import gigjob.model.response.JobDetailResponse;
import gigjob.model.response.JobResponse;

import java.util.List;

public interface JobService {
    JobResponse addJob(JobRequest jobRequest);

//    List<JobResponse> getJobListRedis();

    List<JobDetailResponse> getJob();

    JobDetailResponse getJobById(Long id);

    JobResponse updateJob(JobRequest jobRequest);

    String deleteJob(Long id);
}
