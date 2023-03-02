package gigjob.service;

import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;

import java.util.List;

public interface ShopService {
    List<ShopResponse> getShopList();

    ShopResponse addShop(ShopRequest shopRequest);
}
