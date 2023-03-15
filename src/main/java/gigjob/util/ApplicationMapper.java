package gigjob.util;

import gigjob.entity.Application;
import gigjob.entity.Worker;
import gigjob.model.response.ApplicationDetailResponse;
import gigjob.model.response.JobDetailResponse;
import gigjob.model.response.WorkerResponse;
import org.modelmapper.ModelMapper;

public class ApplicationMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static ApplicationDetailResponse toResponse (Application application, JobDetailResponse jobDetailResponse, WorkerResponse worker) {
        var res = modelMapper.map(application, ApplicationDetailResponse.class);
        res.setJob(jobDetailResponse);
        res.setWorker(worker);
        return res;
    }
}
