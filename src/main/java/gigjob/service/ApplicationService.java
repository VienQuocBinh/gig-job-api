package gigjob.service;

import gigjob.entity.Application;
import gigjob.model.request.ApplicationApplyRequest;
import gigjob.model.response.ApplicationResponse;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    void apply(ApplicationApplyRequest applyRequest);

    List<ApplicationResponse> getApplicationsByWorkerId(UUID workerId);

    List<ApplicationResponse> getApplicationsByShopId(UUID shopID);

    ApplicationResponse acceptApplication(ApplicationApplyRequest applyRequest) throws NotFoundException;
    ApplicationResponse rejectApplication(ApplicationApplyRequest applyRequest) throws NotFoundException;

    List<ApplicationResponse> findAcceptedApplications(Long id);
    List<ApplicationResponse> findRejectedApplications(Long id);
}
