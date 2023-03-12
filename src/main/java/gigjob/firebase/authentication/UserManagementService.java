package gigjob.firebase.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import gigjob.model.response.AccountResponse;
import gigjob.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementService {
    private final AccountService accountService;

    public AccountResponse getFirebaseUserById(String uid) throws FirebaseAuthException {
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
        AccountResponse account = accountService.getAccountById(uid);
        account.setImageUrl(userRecord.getPhotoUrl());
        return account;
    }

    public UserRecord getFirebaseUserByEmail(String email) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUserByEmail(email);
    }

    public UserRecord getFirebaseUserByPhoneNumber(String phoneNumber) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUserByPhoneNumber(phoneNumber);
    }
}
