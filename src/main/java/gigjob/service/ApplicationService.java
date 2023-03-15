package gigjob.service;

import gigjob.model.request.ApplicationApplyRequest;
import gigjob.model.response.ApplicationDetailResponse;
import gigjob.model.response.ApplicationResponse;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    void apply(ApplicationApplyRequest applyRequest);

    List<ApplicationResponse> getApplicationsByWorkerId(UUID workerId);

    List<ApplicationDetailResponse> getApplicationsByShopId(UUID shopID);

    ApplicationDetailResponse acceptApplication(ApplicationApplyRequest applyRequest) throws NotFoundException;
    ApplicationDetailResponse rejectApplication(ApplicationApplyRequest applyRequest) throws NotFoundException;

    List<ApplicationDetailResponse> findAcceptedApplications(Long id);
    List<ApplicationDetailResponse> findRejectedApplications(Long id);
}
