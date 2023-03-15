package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.entity.Account;
import gigjob.entity.Worker;
import gigjob.model.request.WorkerRegisterRequest;
import gigjob.model.request.WorkerUpdateRequest;
import gigjob.model.response.AccountResponse;
import gigjob.model.response.WorkerResponse;
import gigjob.model.response.WorkerUpdateResponse;
import gigjob.repository.AccountRepository;
import gigjob.repository.WorkerRepository;
import gigjob.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    @Override
    public WorkerResponse getWorkerById(UUID workerID) {
        var worker = getWorker(workerID);
        return modelMapper.map(worker, WorkerResponse.class);
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
    public AccountResponse create(WorkerRegisterRequest workerRegisterRequest) {
        try {
            Worker worker = modelMapper.map(workerRegisterRequest, Worker.class);
            worker.setAccount(accountRepository.findById(worker.getAccount().getId()).
                    orElseThrow(() -> new ResourceNotFoundException("Not found account id: " + worker.getAccount().getId())));
            Worker newWorker = workerRepository.save(worker);

            WorkerResponse workerResponse = modelMapper.map(newWorker, WorkerResponse.class);

            Account account = accountRepository.findById(workerRegisterRequest.getAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found account id: " + workerRegisterRequest.getAccountId()));
            AccountResponse response = modelMapper.map(account, AccountResponse.class);
            response.setWorkerResponse(workerResponse);

            return response;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public Worker get(UUID id) {
        return workerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such worker " + id));
    }

    @Override
    public WorkerUpdateResponse update(WorkerUpdateRequest workerUpdateRequest) {
        try {
            // Get old worker record
            Worker updateWorker = workerRepository.findById(workerUpdateRequest.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found worker id: " + workerUpdateRequest.getId()));
            // Get old account record
            Account updateAccount = accountRepository.findAccountById(workerUpdateRequest.getAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found worker id: " + workerUpdateRequest.getId()));
            // Set new value for account record
            updateAccount.setPassword(workerUpdateRequest.getPassword());
            updateAccount.setPhone(workerUpdateRequest.getPhone());
            updateAccount.setUsername(workerUpdateRequest.getUsername());
            // Set new value for worker record
            updateWorker.setFirstName(workerUpdateRequest.getFirstName());
            updateWorker.setMiddleName(workerUpdateRequest.getMiddleName());
            updateWorker.setLastName(workerUpdateRequest.getLastName());
            updateWorker.setBirthday(workerUpdateRequest.getBirthday());
            updateWorker.setEducation(workerUpdateRequest.getEducation());
            updateWorker.setAccount(updateAccount);
            // Update to db
            updateWorker = workerRepository.save(updateWorker);
            updateAccount = accountRepository.save(updateAccount);
            // Create response entity
            WorkerUpdateResponse response = modelMapper.map(updateWorker, WorkerUpdateResponse.class);
            response.setPassword(updateAccount.getPassword());
            response.setPhone(updateAccount.getPhone());
            response.setUsername(updateAccount.getUsername());

            return response;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
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