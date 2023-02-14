package gigjob.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import gigjob.dto.AccountDTO;
import gigjob.dto.AuthRequest;
import gigjob.entity.ResponseObject;
import gigjob.firebase.authentication.TokenVerifier;
import gigjob.repository.AccountRepository;
import gigjob.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenVerifier tokenVerifier;

    @GetMapping("/v1/account")
    public ResponseEntity<ResponseObject> findAll() {
        List<AccountDTO> accountDTOS = accountRepository.findAll()
                .stream()
                .map(acc -> modelMapper.map(acc, AccountDTO.class)).toList();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", accountDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @PostMapping("/v1/account/authenticate-google")
    public GoogleIdToken.Payload authenticateAndGetToken(@RequestHeader String idTokenString) throws IOException {
        return tokenVerifier.validate(idTokenString);
    }

    @PostMapping("/v1/account/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
