package gigjob.service;

import gigjob.common.exception.model.UserNotFoundException;
import gigjob.model.request.AccountRequest;

public interface AccountService {
    AccountRequest getAccountByEmail(String email) throws UserNotFoundException;

    AccountRequest getAccountById(String id) throws UserNotFoundException;

    AccountRequest getAccountByUsername(String username) throws UserNotFoundException;
}
