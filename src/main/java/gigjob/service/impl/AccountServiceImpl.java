package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.common.exception.model.UserNotFoundException;
import gigjob.entity.Account;
import gigjob.firebase.storage.FileStorageService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final FileStorageService fileStorageService;
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
        Optional<Account> optionalAccount = accountRepository.findById(accountRegisterRequest.getId());
        if (optionalAccount.isEmpty()) {
            Account account = new Account();
            account.setId(accountRegisterRequest.getId());
            account.setEmail(accountRegisterRequest.getEmail());
//            account.setPassword(accountRegisterRequest.getPassword());
            account.setUsername(accountRegisterRequest.getUsername());
            return modelMapper.map(accountRepository.save(account), AccountResponse.class);
        } else {
            throw new InternalServerErrorException("Account id: " + accountRegisterRequest.getId() + " already existed");
        }
    }

    @Override
    public String updateImage(String id, MultipartFile file) {
        try {
            String newImageUrl;

            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found account id: " + id));
            String oldImageUrl = account.getImageUrl();

            // Check if Image Url not empty  -> delete the old one on firebase
            // get file name from old url
            if (oldImageUrl != null && !oldImageUrl.isEmpty()) fileStorageService.deleteImage("filename");

            // upload to firebase
            String fileName = fileStorageService.saveFile(file);
            newImageUrl = fileStorageService.getImageUrl(fileName);

            // Update Image Url
            account.setImageUrl(newImageUrl);
            accountRepository.save(account);
            return newImageUrl;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
