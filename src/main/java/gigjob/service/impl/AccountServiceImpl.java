package gigjob.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.common.exception.model.UserNotFoundException;
import gigjob.common.meta.Role;
import gigjob.entity.Account;
import gigjob.entity.Address;
import gigjob.entity.Shop;
import gigjob.entity.Wallet;
import gigjob.firebase.storage.FileStorageService;
import gigjob.model.request.AccountRegisterRequest;
import gigjob.model.request.AccountRequest;
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

import java.time.Instant;
import java.util.Date;
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
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(accountRegisterRequest.getEmail());
            Optional<Account> optionalAccount = accountRepository.findById(userRecord.getUid());
            if (optionalAccount.isEmpty()) {
                Account account = new Account();
                account.setId(userRecord.getUid());
                account.setEmail(accountRegisterRequest.getEmail());
//            account.setPassword(accountRegisterRequest.getPassword());
                account.setPassword("123456");
                account.setImageUrl(userRecord.getPhotoUrl());
                account.setUsername(accountRegisterRequest.getUsername());
                return modelMapper.map(accountRepository.save(account), AccountResponse.class);
            } else {
                throw new InternalServerErrorException("Account id: " + userRecord.getUid() + " already existed");
            }
        } catch (FirebaseAuthException e) {
            throw new InternalServerErrorException(e.getMessage());
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
            if (oldImageUrl != null)
                fileStorageService.deleteImage(fileStorageService.getFilename(oldImageUrl));

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

    @Override
    public AccountResponse registerNewShop(AccountRequest accountRequest) {
        var account = modelMapper.map(accountRequest, Account.class);
        var shop = modelMapper.map(accountRequest, Shop.class);
        account.setCreatedDate(Date.from(Instant.now()));
        account.setUpdatedDate(Date.from(Instant.now()));
        account.setRole(Role.SHOP);
        var address = modelMapper.map(accountRequest.getAddress(), Address.class);
        address.setAccount(account);
        account.setAddresses(List.of(address));
        shop.setAccount(account);
        account.setShop(shop);
        var wallet = Wallet.builder().account(account).balance(0.0).build();
        account.setWallet(wallet);
        accountRepository.save(account);
        var shopQ = accountRepository.findById(account.getId());
        return shopQ.map(value -> modelMapper.map(value, AccountResponse.class)).orElse(null);
    }

    @Override
    public String getImageUrl(String id) {
        var account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found in the database"));
        return account.getImageUrl();
    }
}
