package gigjob.service;

import gigjob.entity.Worker;
import gigjob.model.request.WorkerRegisterRequest;
import gigjob.model.request.WorkerUpdateRequest;
import gigjob.model.response.AccountResponse;
import gigjob.model.response.WorkerDetailResponse;

import java.util.List;
import java.util.UUID;

public interface WorkerService {

    WorkerDetailResponse getWorkerById(UUID workerID);

    Worker getWorker(UUID id);

    List<Worker> ListAll();

    AccountResponse create(WorkerRegisterRequest workerRegisterRequest);

    Worker get(UUID id);

    /**
     * Update Worker info
     *
     * @param workerUpdateRequest {@code workerUpdateRequest}
     * @return {@code WorkerDetailResponse}
     * @author Vien Binh
     */
    WorkerDetailResponse update(WorkerUpdateRequest workerUpdateRequest);

    WorkerDetailResponse getByAccountId(String accountId);

    void delete(UUID id);
}
