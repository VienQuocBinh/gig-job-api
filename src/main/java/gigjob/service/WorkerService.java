package gigjob.service;

import gigjob.entity.Worker;
import gigjob.model.request.WorkerRegisterRequest;
import gigjob.model.request.WorkerUpdateRequest;
import gigjob.model.response.AccountResponse;
import gigjob.model.response.WorkerResponse;
import gigjob.model.response.WorkerUpdateResponse;

import java.util.List;
import java.util.UUID;

public interface WorkerService {

    WorkerResponse getWorkerById(UUID workerID);

    Worker getWorker(UUID id);

    List<Worker> ListAll();

    AccountResponse create(WorkerRegisterRequest workerRegisterRequest);

    Worker get(UUID id);

    /**
     * Update Worker info
     *
     * @param workerUpdateRequest {@code workerUpdateRequest}
     * @return {@code WorkerUpdateResponse}
     * @author Vien Binh
     */
    WorkerUpdateResponse update(WorkerUpdateRequest workerUpdateRequest);

    WorkerResponse getByAccountId(String accountId);

    void delete(UUID id);
}
