package gigjob.service.impl;

import gigjob.common.exception.model.UserNotFoundException;
import gigjob.entity.Account;
import gigjob.model.request.AccountRegisterRequest;
import gigjob.model.response.AccountResponse;
import gigjob.repository.AccountRepository;
import gigjob.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public AccountResponse getAccountByEmail(String email) throws UserNotFoundException {
        Account account = accountRepository.findAccountByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    public AccountResponse getAccountById(String id) throws UserNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id + " not found"));
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    public AccountResponse getAccountByUsername(String username) throws UserNotFoundException {
        Account account = accountRepository.findAccountByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    public List<AccountResponse> getAccountList() {
//        System.out.println("Get from db");
        return accountRepository.findAll()
                .stream()
                .map(acc -> modelMapper.map(acc, AccountResponse.class))
                .toList();
    }

    @Override
    public AccountResponse createAccount(AccountRegisterRequest accountRegisterRequest) {
        Optional<Account> account = accountRepository.findById(accountRegisterRequest.getId());
        if (account.isEmpty()) {
            Account registerAccount = new Account();
            registerAccount.setId(accountRegisterRequest.getId());
            registerAccount.setEmail(accountRegisterRequest.getEmail());
            registerAccount.setPassword(accountRegisterRequest.getPassword());
            registerAccount.setUsername(accountRegisterRequest.getUsername());
            return modelMapper.map(accountRepository.save(registerAccount), AccountResponse.class);
        } else {
            return null;
        }
    }
}
