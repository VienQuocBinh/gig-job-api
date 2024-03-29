package gigjob.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.firebase.auth.FirebaseAuthException;
import gigjob.entity.ResponseObject;
import gigjob.firebase.authentication.TokenVerifier;
import gigjob.firebase.authentication.UserManagementService;
import gigjob.model.request.AccountRegisterRequest;
import gigjob.model.request.AccountRequest;
import gigjob.model.request.AuthRequest;
import gigjob.model.request.WalletRequest;
import gigjob.model.response.AccountResponse;
import gigjob.model.response.JwtResponse;
import gigjob.service.AccountService;
import gigjob.service.WalletService;
import gigjob.service.impl.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenVerifier tokenVerifier;
    private final UserManagementService userManagementService;
    private final WalletService walletService;

    @GetMapping("/v1/account/firebase/{uid}")
    @CrossOrigin
    public AccountResponse getFirebaseUserById(@PathVariable String uid) throws FirebaseAuthException {
        return userManagementService.getFirebaseUserById(uid);
    }

    @PostMapping("/v1/account/register")
    @CrossOrigin
    public AccountResponse registerUser(@RequestBody AccountRegisterRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }

    @PostMapping("/v1/account/register/shop")
    @CrossOrigin
    public ResponseEntity<AccountResponse> registerNewShop(@RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(accountService.registerNewShop(accountRequest));
    }

    @GetMapping("/v1/account")
    @Operation(summary = "ADMIN")
    public ResponseEntity<ResponseObject> findAll() {
        List<AccountResponse> accountResponses = accountService.getAccountList();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", accountResponses);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @PostMapping("/v1/account/login/google")
    @SecurityRequirement(name = "google")
    @CrossOrigin
    @Operation(summary = "For login by Google", description = "Get idToken from Google and decode")
    public ResponseEntity<JwtResponse> authenticateAndGetToken(@Valid @RequestHeader String idTokenString) {
        ResponseEntity<JwtResponse> response;
        GoogleIdToken.Payload payload = tokenVerifier.validate(idTokenString);
        try {
            // create account if fpt mail and account wallet
            if (payload.getEmail().matches("[a-zA-Z0-9]+@fpt.edu.vn$")) {
                AccountResponse accountResponse = accountService.createAccount(
                        new AccountRegisterRequest(payload.getEmail(), payload.getEmail()));
                walletService.create(new WalletRequest(accountResponse.getId(), 0D));
            }
        } catch (Exception e) {
            // Already has an account -> just generate token
        }
        response = ResponseEntity.ok(new JwtResponse(jwtService.generateToken(payload.getEmail())));
        return response;
    }

    @PostMapping("/v1/account/login")
    @CrossOrigin()
    public ResponseEntity<JwtResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new JwtResponse(jwtService.generateToken(authRequest.getEmail())));
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping(value = "/v1/account/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    public ResponseEntity<String> updateImage(@PathVariable String id,
                                              @RequestParam(name = "file") MultipartFile file) {
        String newUrl = accountService.updateImage(id, file);
        return ResponseEntity.ok().body(newUrl);
    }

    @GetMapping("/v1/account/avatar/{id}")
    @CrossOrigin
    public ResponseEntity<String> getAccountAvatar(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getImageUrl(id));
    }
}
