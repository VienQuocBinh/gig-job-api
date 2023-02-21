package gigjob.service;

import com.google.firebase.ErrorCode;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import gigjob.firebase.authentication.UserManagementService;
import gigjob.model.response.AccountResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final AccountService accountService;
    @Value("${security.secret}")
    private String SECRET;
    @Value("${security.jwt.expiration}")
    private long EXPIRED_TIME;

    /**
     * Extract the subject of the JWT
     *
     * @param token {@code String}
     * @return JWT subject value
     * @author Vien Binh
     */
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validate the JWT based on UserDetails information are email and encoded password.
     *
     * @param token       {@link String}
     * @param userDetails {@link UserDetails}
     * @return {@link Boolean}
     * @author Vien Binh
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String subject = extractSubject(token);
        // username of UserDetails is email of UserInfoUserDetails
        return (subject.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String email) throws FirebaseAuthException {
        // Find user in DB by email
        AccountResponse accountResponse = accountService.getAccountByEmail(email);
        // Get Firebase user information by email from login
        UserManagementService userManagementService = new UserManagementService();
        UserRecord userRecord = userManagementService.getFirebaseUserByEmail(email);
        if (userRecord == null) {
            throw new FirebaseAuthException(new FirebaseException(ErrorCode.ABORTED,
                    "No Firebase user information",
                    new NullPointerException()));
        }
        // Put extra information in the token
        Map<String, Object> claims = new HashMap<>();
        claims.put("account", accountResponse);
        // Set the subject is the user email
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }


    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
