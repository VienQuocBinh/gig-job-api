package gigjob.util;

import gigjob.entity.Application;
import gigjob.entity.Worker;
import gigjob.model.response.ApplicationResponse;
import gigjob.model.response.JobDetailResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

public class ApplicationMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static ApplicationResponse toResponse (Application application, JobDetailResponse jobDetailResponse) {
        var res = modelMapper.map(application, ApplicationResponse.class);
        res.setJob(jobDetailResponse);
        return res;
    }
}
