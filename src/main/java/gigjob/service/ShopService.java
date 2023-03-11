package gigjob.service;

import gigjob.entity.Shop;
import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShopService {
    List<ShopResponse> getShopList();

    ShopResponse addShop(ShopRequest shopRequest);

    ShopResponse findShopByAccountId(String accountId);
}
