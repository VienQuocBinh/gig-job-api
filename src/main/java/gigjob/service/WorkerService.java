package gigjob.service;

import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;

import java.util.List;

public interface WorkerService {
    List<Worker> ListAll();

    void save(Worker worker);

    Worker get(Integer id);

    WorkerResponse getByAccountId(String accountId);

    void delete(Integer id);
}
