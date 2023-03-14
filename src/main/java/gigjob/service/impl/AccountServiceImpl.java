package gigjob.service.impl;

import gigjob.common.exception.model.UserNotFoundException;
import gigjob.common.meta.Role;
import gigjob.entity.Account;
import gigjob.entity.Shop;
import gigjob.model.request.AccountRequest;
import gigjob.model.response.AccountResponse;
import gigjob.repository.AccountRepository;
import gigjob.repository.ShopRepository;
import gigjob.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    private final ShopRepository shopRepository;

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
    public AccountResponse createAccount(AccountRequest accountRequest) {
        var account = modelMapper.map(accountRequest, Account.class);
        var shop = modelMapper.map(accountRequest, Shop.class);
        account.setCreatedDate(Date.from(Instant.now()));
        account.setUpdatedDate(Date.from(Instant.now()));
        account.setRole(Role.SHOP);
        shop.setAccount(account);
//        accountRepository.save(account);
        shopRepository.save(shop);
        var shopQ = accountRepository.findById(account.getId());
        return shopQ.map(value -> modelMapper.map(value, AccountResponse.class)).orElse(null);
    }
}
