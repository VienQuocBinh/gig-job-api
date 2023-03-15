package gigjob.service;

import gigjob.entity.Job;
import gigjob.model.domain.SearchCriteria;
import gigjob.model.request.JobRequest;
import gigjob.model.response.JobDetailResponse;
import gigjob.model.response.JobResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobService {
    JobResponse addJob(JobRequest jobRequest);

    /**
     * The first time get Job from Database at  cached in Redis with the key {@code jobs::SimpleKey [] }
     * next time you call the method with the same arguments, the cached list of users will be returned directly from Redis cache without hitting the database.
     *
     * @return {@code  List<JobResponse>}
     * @author Vien Binh
     */
    List<JobDetailResponse> getJob();

    JobDetailResponse getJobById(Long id);

    JobResponse updateJob(JobRequest jobRequest);

    String deleteJob(Long id);

    Optional<Job> findJobById(Long id);

    List<JobDetailResponse> findJobsByShopId(UUID id);

    List<JobDetailResponse> searchJob(SearchCriteria searchCriteria, int pageIndex, int pageSize);
}
