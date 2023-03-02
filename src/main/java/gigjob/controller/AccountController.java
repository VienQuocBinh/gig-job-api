package gigjob.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import gigjob.entity.ResponseObject;
import gigjob.firebase.authentication.TokenVerifier;
import gigjob.firebase.authentication.UserManagementService;
import gigjob.model.request.AuthRequest;
import gigjob.model.response.AccountResponse;
import gigjob.model.response.JwtResponse;
import gigjob.service.AccountService;
import gigjob.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenVerifier tokenVerifier;
    private final UserManagementService userManagementService;

    @GetMapping("/v1/firebase/user/{uid}")
    public UserRecord getUserById(@PathVariable String uid) throws FirebaseAuthException {
        return userManagementService.getFirebaseUserById(uid);
    }


    @PostMapping("/v1/register")
    public AccountResponse registerUser(@RequestBody AccountResponse accountResponse) {
        return accountResponse;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/v1/account")
    @Operation(summary = "ADMIN")
    public ResponseEntity<ResponseObject> findAll() {
        List<AccountResponse> accountResponses = accountService.getAccountList();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", accountResponses);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @GetMapping("/v1/account/redis")
    @Operation(summary = "ADMIN")
    public ResponseEntity<ResponseObject> findAllRedis() {
        List<AccountResponse> accountResponses = accountService.getAccountListRedis();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", accountResponses);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @PostMapping("/v1/account/login/google")
    @SecurityRequirement(name = "google")
    @Operation(summary = "For login by Google", description = "Get idToken from Google and decode")
    public ResponseEntity<JwtResponse> authenticateAndGetToken(@Valid @RequestHeader String idTokenString) throws IOException, FirebaseAuthException {
        GoogleIdToken.Payload payload = tokenVerifier.validate(idTokenString);
        return ResponseEntity.ok(new JwtResponse(jwtService.generateToken(payload.getEmail())));
    }

    @PostMapping("/v1/account/login")
    public ResponseEntity<JwtResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws FirebaseAuthException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new JwtResponse(jwtService.generateToken(authRequest.getEmail())));
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
