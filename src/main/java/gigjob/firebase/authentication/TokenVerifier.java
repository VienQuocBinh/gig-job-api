package gigjob.firebase.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.gson.GsonFactory;
import gigjob.common.exception.model.AudienceMismatchException;
import gigjob.common.exception.model.ClientIdMismatchException;
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
    private final String GOOGLE_CLIENT_ID = "395540117439-hkbj5lk2hnrtcteihk7cv8gtsmmmbp3u.apps.googleusercontent.com";
    // 3 Authorized Parties: Android, iOS, Web apps
    private final List<String> GOOGLE_AUTHORIZED_PARTIES = List.of(new String[]{
            "395540117439-2pm5vil960v7n488vm6sdj7sdrv9jv5s.apps.googleusercontent.com",
            "395540117439-hriqo5e2ka4g8dotnh77a8uog4u4olh2.apps.googleusercontent.com",
            "395540117439-hkbj5lk2hnrtcteihk7cv8gtsmmmbp3u.apps.googleusercontent.com",
            "395540117439-vm7157r90l3c0devegctefnll7qpr78m.apps.googleusercontent.com"});

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
//        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier(new NetHttpTransport(), gsonFactory);
        GoogleIdToken idToken = GoogleIdToken.parse(gsonFactory, idTokenString);
        GoogleIdToken.Payload payload = idToken.getPayload();
        if (!GOOGLE_CLIENT_ID.equals(payload.getAudience())) {
            throw new AudienceMismatchException("Audience mismatch");
        } else if (!GOOGLE_AUTHORIZED_PARTIES.contains(payload.getAuthorizedParty())) {
            throw new ClientIdMismatchException("Client ID mismatch");
        }
        return payload;
    }
}
