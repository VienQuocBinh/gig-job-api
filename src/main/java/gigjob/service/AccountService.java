package gigjob.service;

import gigjob.common.exception.model.UserNotFoundException;
import gigjob.model.request.AccountRegisterRequest;
import gigjob.model.request.AccountRequest;
import gigjob.model.response.AccountResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {
    AccountResponse getAccountByEmail(String email) throws UserNotFoundException;

    AccountResponse getAccountById(String id) throws UserNotFoundException;

    AccountResponse getAccountByUsername(String username) throws UserNotFoundException;

    List<AccountResponse> getAccountList();

    AccountResponse createAccount(AccountRegisterRequest accountRegisterRequest);

    String updateImage(String id, MultipartFile file);

    AccountResponse registerNewShop(AccountRequest accountRequest);

    String getImageUrl(String id);
}
