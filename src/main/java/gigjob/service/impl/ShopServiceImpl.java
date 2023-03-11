package gigjob.service.impl;

import gigjob.entity.Shop;
import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;
import gigjob.repository.ShopRepository;
import gigjob.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ModelMapper modelMapper;
    private final ShopRepository shopRepository;

    @Override
    public List<ShopResponse> getShopList() {
        return shopRepository.findAll().stream().map(shop -> {
            ShopResponse response = modelMapper.map(shop, ShopResponse.class);
            return modelMapper.map(shop, ShopResponse.class);
        }).collect(Collectors.toList());
    }

    @Override
    public ShopResponse addShop(ShopRequest shopRequest) {
        Shop shop = modelMapper.map(shopRequest, Shop.class);
        shopRepository.save(shop);
        return modelMapper.map(shop, ShopResponse.class);
    }

    @Override
    public ShopResponse findShopByAccountId(String accountId) {
        var shop = shopRepository.findByAccountId(accountId);
        if (shop.isEmpty()) throw new NotFoundException("Shop not found");
        return modelMapper.map(shop.get(), ShopResponse.class);

    }
}
