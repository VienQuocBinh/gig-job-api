package gigjob.service;

import gigjob.common.exception.model.UserNotFoundException;
import gigjob.dto.AccountDTO;

public interface AccountService {
    AccountDTO getAccountByEmail(String email) throws UserNotFoundException;

    AccountDTO getAccountById(String id) throws UserNotFoundException;
}
