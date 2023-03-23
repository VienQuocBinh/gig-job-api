package gigjob.service;

import gigjob.model.request.NewShopProfileRequest;
import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;

import java.util.List;

public interface ShopService {
    List<ShopResponse> getShopList();

    ShopResponse addShop(ShopRequest shopRequest);

    ShopResponse findShopByAccountId(String accountId);

    ShopResponse createNewShopProfile(NewShopProfileRequest request);

    ShopResponse updateShopProfile(ShopRequest shopRequest);

    List<ShopResponse> getNearbyShop(double latitude, double longitude);
}
