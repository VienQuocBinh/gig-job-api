package gigjob.service.impl;

import com.sun.xml.bind.v2.runtime.SwaRefAdapter;
import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;
import gigjob.repository.AccountRepository;
import gigjob.repository.WorkerRepository;
import gigjob.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;

    private final AccountRepository accountRepository;
    @Override
    public WorkerResponse getWorkerById(UUID workerID) {
        var worker =  getWorker(workerID);
        var wRes = modelMapper.map(worker, WorkerResponse.class);
        var acc = accountRepository.findAccountById(worker.getAccount().getId()).get();
        wRes.setEmail(acc.getEmail());
        wRes.setPhone(acc.getPhone());
        wRes.setImgUrl(acc.getImage_url());
        wRes.setAddresses(acc.getAddresses());
        return wRes;
    }

    @Override
    public Worker getWorker(UUID id) {
        return workerRepository.getWorkerById(id);
    }
}
