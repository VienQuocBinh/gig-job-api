package gigjob.controller;

import gigjob.model.response.AddressResponse;
import gigjob.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/v1/address/account/{id}")
    public ResponseEntity<List<AddressResponse>> getAddressesFromAccountId(@PathVariable String id){
        return ResponseEntity.ok(addressService.getAddressResponsesFromAccountId(id));
    }

    @GetMapping("/v1/address/{id}")
    public ResponseEntity<AddressResponse> getAddressFromId(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressResponseFromId(id));
    }
}
