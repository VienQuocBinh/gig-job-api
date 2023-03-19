package gigjob.controller;

import gigjob.entity.ResponseObject;
import gigjob.entity.Wallet;
import gigjob.model.request.WalletRequest;
import gigjob.model.response.WalletResponse;
import gigjob.repository.AccountRepository;
import gigjob.repository.WalletRepository;
import gigjob.service.WalletService;
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
    private final WalletService walletService;

    @GetMapping("/v1/wallet")
    public ResponseEntity<ResponseObject> getWalletList() {
        List<WalletResponse> walletResponses = walletRepository.findAll()
                .stream()
                .map(WalletMapper::toDto)
                .toList();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", walletResponses);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @GetMapping("/v1/wallet/account/{id}")
    public ResponseEntity<WalletResponse> getWalletListByAccountId(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.OK).body(walletService.getByAccountId(id));
    }

    @PostMapping("/v1/wallet")
    public ResponseEntity<WalletResponse> create(@RequestBody WalletRequest walletRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(walletService.create(walletRequest));
    }

    @PutMapping("/v1/wallet/{id}")
    public ResponseEntity<?> update(@RequestBody Wallet wallet,
                                    @PathVariable UUID id) {
        try {
            Wallet existWallet = walletService.get(id);
            walletService.create(null);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/v1/wallet/{id}")
    public void delete(@PathVariable UUID id) {
        walletService.delete(id);
    }
}
