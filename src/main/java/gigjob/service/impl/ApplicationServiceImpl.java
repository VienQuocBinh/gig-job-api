package gigjob.service.impl;

import gigjob.entity.Application;
import gigjob.model.request.ApplicationApplyRequest;
import gigjob.model.response.ApplicationResponse;
import gigjob.repository.ApplicationRepository;
import gigjob.service.ApplicationService;
import gigjob.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ModelMapper modelMapper;
    private final ApplicationRepository applicationRepository;
    private final JobService jobService;

    @Override
    public void apply(ApplicationApplyRequest applyRequest) {
        Application application = modelMapper.map(applyRequest, Application.class);
        applicationRepository.save(application);
    }

    @Override
    public List<ApplicationResponse> getApplicationsByWorkerId(UUID workerId) {
        List<Application> applications = applicationRepository.findApplicationsByWorkerId(workerId);

        return applications.stream()
                .map(application -> {
                    ApplicationResponse applicationResponse = modelMapper.map(application, ApplicationResponse.class);
                    applicationResponse.setJob(jobService.getJobById(application.getId().getJob().getId()));
                    return applicationResponse;
                })
                .toList();
    }
}
