package gigjob.service.impl;

import gigjob.entity.Shop;
import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;
import gigjob.repository.ShopRepository;
import gigjob.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ModelMapper modelMapper;
    private final ShopRepository shopRepository;

    @Override
    public List<ShopResponse> getShopList() {
        return shopRepository.findAll().stream().map(shop -> modelMapper.map(shop, ShopResponse.class)).toList();
    }

    @Override
    public ShopResponse getShopById(UUID id) {
        return shopRepository.findById(id)
                .map(shop -> modelMapper.map(shop, ShopResponse.class))
                .orElseThrow(() -> new NullPointerException("Shop not found id: " + id));
    }

    @Override
    public ShopResponse addShop(ShopRequest shopRequest) {
        Shop shop = modelMapper.map(shopRequest, Shop.class);
        return modelMapper.map(shopRepository.save(shop), ShopResponse.class);
    }

    @Override
    public String deleteShop(UUID id) {
        shopRepository.deleteById(id);
        return "Delete shop " + id + " successfully";
    }

    @Override
    public ShopResponse updateShop(ShopRequest shopRequest) {
        Shop shop = shopRepository.findById(shopRequest.getId())
                .orElseThrow(() -> new NullPointerException("Shop not found " + shopRequest.getId()));
        return modelMapper.map(shopRepository.save(modelMapper.map(shopRequest, Shop.class)), ShopResponse.class);
    }
}
