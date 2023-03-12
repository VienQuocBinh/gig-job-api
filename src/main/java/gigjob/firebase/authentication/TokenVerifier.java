package gigjob.firebase.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * For verifying the id token
 *
 * @author Vien Binh
 */
@Component
public class TokenVerifier {
    // Audience ids
    private final List<String> GOOGLE_CLIENT_ID = List.of(new String[]{
            "395540117439-hkbj5lk2hnrtcteihk7cv8gtsmmmbp3u.apps.googleusercontent.com",
            "395540117439-tu54if77tfdh8j07r7uoeikkudq7scbq.apps.googleusercontent.com",
            "gigjob-mobile"});
    private final List<String> ISSUERS = List.of(new String[]{
            "https://securetoken.google.com/gigjob-mobile",
            "https://accounts.google.com",
    });

    /**
     * Verify id token from Firebase
     *
     * @param idTokenString {@code String}
     * @return {@code GoogleIdToken.Payload}
     * @throws IOException - if an I/O error occurs
     * @author Vien Binh
     */
    public GoogleIdToken.Payload validate(String idTokenString) throws IOException {
        GsonFactory gsonFactory = new GsonFactory();
        GoogleIdToken idToken = GoogleIdToken.parse(gsonFactory, idTokenString);
        GoogleIdToken.Payload payload = idToken.getPayload();
//        if (!GOOGLE_CLIENT_ID.contains(payload.getAudience().toString())) {
//            throw new AudienceMismatchException("Audience mismatch");
//        } else if (!ISSUERS.contains(payload.getIssuer())) {
//            throw new IssuerMismatchException("Issuer mismatch");
//        }
        return payload;
    }
}
