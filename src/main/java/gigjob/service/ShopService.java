package gigjob.service;

import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;

import java.util.List;
import java.util.UUID;

public interface ShopService {
    List<ShopResponse> getShopList();

    ShopResponse getShopById(UUID id);

    ShopResponse addShop(ShopRequest shopRequest);

    String deleteShop(UUID id);

    ShopResponse updateShop(ShopRequest shopRequest);
}
