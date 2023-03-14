package gigjob.service.impl;

import gigjob.entity.Account;
import gigjob.entity.Shop;
import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;
import gigjob.repository.AccountRepository;
import gigjob.repository.ShopRepository;
import gigjob.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ModelMapper modelMapper;
    private final ShopRepository shopRepository;

    private final AccountRepository accountRepository;

    @Override
    public List<ShopResponse> getShopList() {
        return shopRepository.findAll().stream().map(shop -> modelMapper.map(shop, ShopResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ShopResponse addShop(ShopRequest shopRequest) {
        Shop shop = modelMapper.map(shopRequest, Shop.class);
        var account = modelMapper.map(shopRequest, Account.class);
        accountRepository.save(account);
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
