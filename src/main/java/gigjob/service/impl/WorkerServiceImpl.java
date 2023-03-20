package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.common.meta.Role;
import gigjob.common.util.WorkerUtil;
import gigjob.entity.Account;
import gigjob.entity.Worker;
import gigjob.model.request.WorkerRegisterRequest;
import gigjob.model.request.WorkerUpdateRequest;
import gigjob.model.response.AccountResponse;
import gigjob.model.response.WorkerDetailResponse;
import gigjob.model.response.WorkerResponse;
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
    private final WorkerUtil workerUtil;

    @Override
    public WorkerDetailResponse getWorkerById(UUID workerID) {
        try {
            Worker worker = workerRepository.findById(workerID)
                    .orElseThrow(() -> new ResourceNotFoundException("Worker not found for workerID: " + workerID));
            Account account = accountRepository.findById(worker.getAccount().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found account id: " + worker.getAccount().getId()));
            return workerUtil.setAccountInfo(worker, account);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
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
            // Get old account record (dup public WorkerDetailResponse update(WorkerUpdateRequest workerUpdateRequest))
            Account account = accountRepository.findById(workerRegisterRequest.getAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found account id: " + workerRegisterRequest.getAccountId()));
            // Set new account value
            account.setPassword(workerRegisterRequest.getPassword());
            account.setPhone(workerRegisterRequest.getPhone());
            account.setUsername(workerRegisterRequest.getUsername());
            account.setRole(Role.WORKER);
            // Get old worker record
            Worker worker = modelMapper.map(workerRegisterRequest, Worker.class);
            // Set new account info
            worker.setAccount(account);
            // save new worker and account belongs to it
            Worker newWorker = workerRepository.save(worker);
            // Creat response entity
            WorkerResponse workerResponse = modelMapper.map(newWorker, WorkerResponse.class);
            AccountResponse response = modelMapper.map(account, AccountResponse.class);
            response.setWorkerDetail(workerResponse);
            response.setImageUrl(account.getImageUrl());
            response.setPhone(account.getPhone());

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
    public WorkerDetailResponse update(WorkerUpdateRequest workerUpdateRequest) {
        try {
            // Get old worker
            Worker updateWorker = workerRepository.findById(workerUpdateRequest.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found worker id: " + workerUpdateRequest.getId()));
            // Get old account info of that worker
            Account updateAccount = updateWorker.getAccount();
            // Set new values to account
            updateAccount.setPassword(workerUpdateRequest.getPassword());
            updateAccount.setPhone(workerUpdateRequest.getPhone());
            updateAccount.setUsername(workerUpdateRequest.getUsername());

            // Set new values to worker
            updateWorker.setFirstName(workerUpdateRequest.getFirstName());
            updateWorker.setMiddleName(workerUpdateRequest.getMiddleName());
            updateWorker.setLastName(workerUpdateRequest.getLastName());
            updateWorker.setBirthday(workerUpdateRequest.getBirthday());
            updateWorker.setEducation(workerUpdateRequest.getEducation());

            // Save worker and account info within it
            updateWorker = workerRepository.save(updateWorker);

            // Create response entity
            return workerUtil.setAccountInfo(updateWorker, updateAccount);

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public WorkerDetailResponse getByAccountId(String accountId) {
        try {
            Worker worker = workerRepository.findByAccountId(accountId);
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found account id: " + accountId));
            return workerUtil.setAccountInfo(worker, account);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        workerRepository.deleteById(id);
    }
}