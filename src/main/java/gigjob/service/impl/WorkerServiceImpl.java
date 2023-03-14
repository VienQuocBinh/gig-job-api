package gigjob.service.impl;

import com.sun.xml.bind.v2.runtime.SwaRefAdapter;
import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;
import gigjob.repository.AccountRepository;
import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;
import gigjob.repository.WorkerRepository;
import gigjob.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;

    private final AccountRepository accountRepository;

    @Override
    public WorkerResponse getWorkerById(UUID workerID) {
        var worker = getWorker(workerID);
        var wRes = modelMapper.map(worker, WorkerResponse.class);
        var acc = accountRepository.findAccountById(worker.getAccount().getId()).get();
        return wRes;
    }

    @Override
    public Worker getWorker(UUID id) {
        return workerRepository.getWorkerById(id);

    }

    @Override
    public List<Worker> ListAll() {
        return workerRepository.findAll();
    }

    @Override
    public void save(Worker worker) {
        workerRepository.save(worker);
    }

    @Override
    public Worker get(UUID id) {
        return workerRepository.findById(id).orElseThrow(() -> new NotFoundException("No such worker " + id));
    }

    @Override
    public WorkerResponse getByAccountId(String accountId) {
        return modelMapper.map(workerRepository.findByAccountId(accountId), WorkerResponse.class);
    }

    @Override
    public void delete(UUID id) {
        workerRepository.deleteById(id);
    }
}