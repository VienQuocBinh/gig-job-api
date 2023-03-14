package gigjob.service;

import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;

import java.util.List;
import java.util.UUID;

public interface WorkerService {

    WorkerResponse getWorkerById(UUID workerID);

    Worker getWorker(UUID id);
    List<Worker> ListAll();

    void save(Worker worker);

    Worker get(UUID id);

    WorkerResponse getByAccountId(String accountId);

    void delete(UUID id);
}
