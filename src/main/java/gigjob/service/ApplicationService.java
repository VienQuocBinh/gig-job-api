package gigjob.service;

import gigjob.model.request.ApplicationApplyRequest;
import gigjob.model.response.ApplicationResponse;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {
    void apply(ApplicationApplyRequest applyRequest);

    List<ApplicationResponse> getApplicationsByWorkerId(UUID workerId);
}
