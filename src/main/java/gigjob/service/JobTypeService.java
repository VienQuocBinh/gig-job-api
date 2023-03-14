package gigjob.service;

import gigjob.model.response.JobTypeResponse;

import java.util.List;

public interface JobTypeService {
    List<JobTypeResponse> getJobTypeResponseList();
}
