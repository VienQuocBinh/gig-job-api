package gigjob.service.impl;

import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;
import gigjob.repository.WorkerRepository;
import gigjob.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final ModelMapper modelMapper;
    private final WorkerRepository workerRepository;

    @Override
    public List<Worker> ListAll() {
        return workerRepository.findAll();
    }

    @Override
    public void save(Worker worker) {
        workerRepository.save(worker);
    }

    @Override
    public Worker get(Integer id) {
        return workerRepository.findById(id).orElseThrow(() -> new NotFoundException("No such worker " + id));
    }

    @Override
    public WorkerResponse getByAccountId(String accountId) {
        return modelMapper.map(workerRepository.findByAccountId(accountId), WorkerResponse.class);
    }

    @Override
    public void delete(Integer id) {
        workerRepository.deleteById(id);
    }
}
