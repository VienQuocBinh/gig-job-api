package gigjob.controller;

import gigjob.model.request.ShopRequest;
import gigjob.model.response.ErrorResponse;
import gigjob.model.response.ShopResponse;
import gigjob.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ShopController {
    private final ModelMapper modelMapper;
    private final ShopService shopService;

    @GetMapping("/v1/shop")
    public ResponseEntity<List<ShopResponse>> getShopList() {
        List<ShopResponse> shopResponseList = shopService.getShopList();
        return ResponseEntity.status(HttpStatus.OK).body(shopResponseList);
    }

    @PostMapping("/v1/shop")
    public ResponseEntity<ShopResponse> createShopAcc(@RequestBody ShopRequest shopRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(shopService.addShop(shopRequest));
    }

    @GetMapping("/v1/shop/{id}")
    public ResponseEntity<Object> searchShop(@PathVariable("id")String accountId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    shopService.findShopByAccountId(accountId)
            );
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ErrorResponse.builder()
                            .error("404")
                            .message(e.getMessage())
                            .timestamp(new Date())
                            .build()
            );
        }
    }
}
