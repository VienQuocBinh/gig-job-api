package gigjob.service;

import gigjob.common.exception.model.UserNotFoundException;
import gigjob.model.response.AccountResponse;

public interface AccountService {
    AccountResponse getAccountByEmail(String email) throws UserNotFoundException;

    AccountResponse getAccountById(String id) throws UserNotFoundException;

    AccountResponse getAccountByUsername(String username) throws UserNotFoundException;
}
