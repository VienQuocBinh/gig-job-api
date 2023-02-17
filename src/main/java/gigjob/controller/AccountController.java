package gigjob.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import gigjob.dto.AccountDTO;
import gigjob.dto.AuthRequest;
import gigjob.dto.ShopDTO;
import gigjob.dto.UserInfoUserDetails;
import gigjob.entity.ResponseObject;
import gigjob.entity.Shop;
import gigjob.firebase.authentication.TokenVerifier;
import gigjob.firebase.authentication.UserManagementService;
import gigjob.repository.AccountRepository;
import gigjob.repository.ShopRepository;
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
    private final AccountRepository accountRepository;
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenVerifier tokenVerifier;
    private final UserManagementService userManagementService;

    @GetMapping("/v1/firebase-user/{uid}")
    public UserRecord getUserById(@PathVariable String uid) throws FirebaseAuthException {
        return userManagementService.getFirebaseUserById(uid);
    }

    @PostMapping("/v1/create-shop")
    public ShopDTO createShopAcc(@RequestBody ShopDTO shopDTO) {
        Shop shop = modelMapper.map(shopDTO, Shop.class);
        shopRepository.save(shop);
        return shopDTO;
    }

    @PostMapping("/v1/create")
    public UserInfoUserDetails createUserInfo(@RequestBody UserInfoUserDetails userInfoUser) {


        return userInfoUser;
    }

    @GetMapping("/v1/account")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> findAll() {
        List<AccountDTO> accountDTOS = accountRepository.findAll()
                .stream()
                .map(acc -> modelMapper.map(acc, AccountDTO.class)).toList();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", accountDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @PostMapping("/v1/account/login-google")
    @SecurityRequirement(name = "google")
    @Operation(summary = "For login", description = "Login from Google")
    public ResponseEntity<String> authenticateAndGetToken(@Valid @RequestHeader String idTokenString) throws IOException, FirebaseAuthException {
        GoogleIdToken.Payload payload = tokenVerifier.validate(idTokenString);
        return ResponseEntity.ok(jwtService.generateTokenByEmail(payload.getEmail()));
    }

    @PostMapping("/v1/account/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
