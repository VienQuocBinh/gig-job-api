package gigjob.common.util;

import gigjob.entity.Account;
import gigjob.entity.Worker;
import gigjob.model.response.WorkerDetailResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkerUtil {
    private final ModelMapper modelMapper;

    public WorkerDetailResponse setAccountInfo(Worker worker, Account account) {
        WorkerDetailResponse workerDetailResponse =
                modelMapper.map(worker, WorkerDetailResponse.class);
        // set account info
        workerDetailResponse.setEmail(account.getEmail());
        workerDetailResponse.setPhone(account.getPhone());
        workerDetailResponse.setUsername(account.getUsername());
        workerDetailResponse.setPassword(account.getPassword());
        workerDetailResponse.setImageUrl(account.getImageUrl());
        return workerDetailResponse;
    }
}
