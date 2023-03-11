package gigjob.service.impl;

import gigjob.common.embeddedkey.ApplicationId;
import gigjob.common.meta.ApplicationStatus;
import gigjob.entity.Application;
import gigjob.model.request.ApplicationApplyRequest;
import gigjob.model.response.ApplicationResponse;
import gigjob.repository.ApplicationRepository;
import gigjob.service.ApplicationService;
import gigjob.service.JobService;
import gigjob.service.WorkerService;
import gigjob.util.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ModelMapper modelMapper;
    private final ApplicationRepository applicationRepository;
    private final WorkerService workerService;
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

    @Override
    public List<ApplicationResponse> getApplicationsByShopId(UUID shopID) {
        return applicationRepository.findApplicationByShopId(shopID)
                .stream().map(
                        a -> ApplicationMapper.toResponse(
                                a,
                                jobService.getJobById(a.getId().getJob().getId())
                        )
                )
                .toList();
    }

    @Override
    public ApplicationResponse acceptApplication(ApplicationApplyRequest applyRequest) throws NotFoundException {
        var application = findJobByID(applyRequest);
        application.setStatus(ApplicationStatus.ACCEPTED);
        application = applicationRepository.save(application);
        return ApplicationMapper.toResponse(application,
                jobService.getJobById(application.getId().getJob().getId()));
    }

    @Override
    public ApplicationResponse rejectApplication(ApplicationApplyRequest applyRequest) throws NotFoundException {
        Application application = findJobByID(applyRequest);
        application.setStatus(ApplicationStatus.REJECTED);
        application = applicationRepository.save(application);
        return ApplicationMapper.toResponse(application,
                jobService.getJobById(application.getId().getJob().getId()));
    }

    private Application findJobByID(ApplicationApplyRequest applyRequest) {
        var job = jobService.findJobById(applyRequest.getJobId());
        if (job.isEmpty()) {
            throw new NotFoundException("Job Not Found");
        }
        var app = applicationRepository.findApplicationById(
                ApplicationId.builder()
                        .job(job.get())
                        .worker(workerService.getWorker(applyRequest.getWorkerId()))
                        .build()
        );
        if (app.isEmpty()) {
            throw new NotFoundException("Job Not Found");
        }
        return app.get();
    }

    @Override
    public List<ApplicationResponse> findAcceptedApplications(Long id) {
        return applicationRepository.findAcceptedByJobId(id)
                .stream().map(
                        a -> ApplicationMapper.toResponse(
                                a,
                                jobService.getJobById(a.getId().getJob().getId())
                        )
                ).toList();
    }

    @Override
    public List<ApplicationResponse> findRejectedApplications(Long id) {
        return applicationRepository.findRejectedByJobId(id)
                .stream().map(
                        a -> ApplicationMapper.toResponse(
                                a,
                                jobService.getJobById(a.getId().getJob().getId())
                        )
                ).toList();
    }
}
