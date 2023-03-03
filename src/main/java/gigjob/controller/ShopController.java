package gigjob.controller;

import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;
import gigjob.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ShopController {
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
}
