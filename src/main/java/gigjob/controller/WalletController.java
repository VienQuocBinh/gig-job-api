package gigjob.controller;

import gigjob.entity.Account;
import gigjob.entity.ResponseObject;
import gigjob.entity.Wallet;
import gigjob.model.response.WalletResponse;
import gigjob.repository.AccountRepository;
import gigjob.repository.WalletRepository;
import gigjob.service.impl.WalletServiceImpl;
import gigjob.util.WalletMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class WalletController {
    private final ModelMapper modelMapper;
    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;
    private final WalletServiceImpl service;

    @GetMapping("/v1/wallet")
    public ResponseEntity<ResponseObject> getAll() {
        List<WalletResponse> walletResponses = walletRepository.findAll()
                .stream()
                .map(WalletMapper::toDto)
                .toList();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", walletResponses);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @PostMapping("/v1/wallet")
    public ResponseEntity<ResponseObject> create(@RequestBody WalletResponse walletResponse) {
        Wallet wallet = modelMapper.map(walletResponse, Wallet.class);
        Account account = accountRepository.findById(walletResponse.getAccountId()).orElse(null);
        ResponseObject responseObject;
        HttpStatus status = HttpStatus.OK;
        if (account != null) {
            wallet.setAccount(account);
            walletRepository.save(wallet);
            responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", walletResponse);
        } else {
            status = HttpStatus.NOT_FOUND;
            responseObject = new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found account", null);
        }
        return new ResponseEntity<>(responseObject, status);
    }

    @PutMapping("/Wallets/{id}")
    public ResponseEntity<?> update(@RequestBody Wallet wallet,
                                    @PathVariable UUID id) {
        try {
            Wallet existWallet = service.get(id);
            service.save(wallet);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Wallets/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
