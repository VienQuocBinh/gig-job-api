package gigjob.service;

import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;

import java.util.UUID;

public interface WorkerService {

    WorkerResponse getWorkerById(UUID workerID);

    Worker getWorker(UUID id);
}
