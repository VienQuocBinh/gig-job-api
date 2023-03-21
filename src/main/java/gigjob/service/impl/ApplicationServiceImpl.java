package gigjob.service.impl;

import gigjob.common.embeddedkey.ApplicationId;
import gigjob.common.meta.ApplicationStatus;
import gigjob.entity.Application;
import gigjob.model.request.ApplicationApplyRequest;
import gigjob.model.response.ApplicationDetailResponse;
import gigjob.model.response.ApplicationResponse;
import gigjob.repository.ApplicationRepository;
import gigjob.service.ApplicationService;
import gigjob.service.JobService;
import gigjob.service.WorkerService;
import gigjob.util.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
    public List<ApplicationDetailResponse> getApplicationsByShopId(UUID shopID) {
        var apps = applicationRepository.findApplicationByShopId(shopID);
        var appRes = apps.stream().map(a -> ApplicationMapper.toResponse(a, jobService.getJobById(a.getId().getJob().getId()),workerService.getWorkerById(a.getId().getWorker().getId())));
        return appRes.collect(Collectors.toList());
    }

    @Override
    public ApplicationDetailResponse acceptApplication(ApplicationApplyRequest applyRequest) throws NotFoundException {
        var application = findJobByID(applyRequest);
        application.setStatus(ApplicationStatus.ACCEPTED);
        application = applicationRepository.save(application);
        return ApplicationMapper.toResponse(application,
                jobService.getJobById(application.getId().getJob().getId()),
                workerService.getWorkerById(application.getId().getWorker().getId()));
    }

    @Override
    public ApplicationDetailResponse rejectApplication(ApplicationApplyRequest applyRequest) throws NotFoundException {
        Application application = findJobByID(applyRequest);
        application.setStatus(ApplicationStatus.REJECTED);
        application = applicationRepository.save(application);
        return ApplicationMapper.toResponse(application,
                jobService.getJobById(application.getId().getJob().getId()),
                workerService.getWorkerById(application.getId().getWorker().getId()));
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
    public List<ApplicationDetailResponse> findAcceptedApplications(Long id) {
        return applicationRepository.findAcceptedByJobId(id)
                .stream().map(
                        a -> ApplicationMapper.toResponse(
                                a,
                                jobService.getJobById(a.getId().getJob().getId()),
                                workerService.getWorkerById(a.getId().getWorker().getId())
                        )
                ).toList();
    }

    @Override
    public List<ApplicationDetailResponse> findAcceptedApplications(UUID shopId) {
        return applicationRepository.findAcceptedApplicationByShopId(shopId)
                .stream()
                .map(
                        a -> ApplicationMapper.toResponse(
                                a,
                                jobService.getJobById(a.getId().getJob().getId()),
                                workerService.getWorkerById(a.getId().getWorker().getId())
                        )
                ).toList();
    }

    @Override
    public List<ApplicationDetailResponse> findRejectedApplications(Long id) {
        return applicationRepository.findRejectedByJobId(id)
                .stream().map(
                        a -> ApplicationMapper.toResponse(
                                a,
                                jobService.getJobById(a.getId().getJob().getId()),
                                workerService.getWorkerById(a.getId().getWorker().getId())
                        )
                ).toList();
    }
}
