package gigjob.firebase.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
    public UserRecord getFirebaseUserById(String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(uid);
    }

    public UserRecord getFirebaseUserByEmail(String email) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUserByEmail(email);
    }

    public UserRecord getFirebaseUserByPhoneNumber(String phoneNumber) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUserByPhoneNumber(phoneNumber);
    }
}
