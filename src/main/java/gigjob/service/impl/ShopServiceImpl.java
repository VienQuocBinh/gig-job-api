package gigjob.service.impl;

import gigjob.common.meta.Role;
import gigjob.entity.Account;
import gigjob.entity.Address;
import gigjob.entity.Shop;
import gigjob.model.request.AddressRequest;
import gigjob.model.request.NewShopProfileRequest;
import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;
import gigjob.repository.AccountRepository;
import gigjob.repository.ShopRepository;
import gigjob.service.AddressService;
import gigjob.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ModelMapper modelMapper;
    private final ShopRepository shopRepository;

    private final AccountRepository accountRepository;
    private final AddressService addressService;

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
        var shop = shopRepository.findByAccountId(accountId).orElse(null);
        if (shop == null) throw new NotFoundException("Shop not found");
        var addresses = addressService.getAddressResponsesFromAccountId(accountId);
        var mapper = modelMapper.map(shop, ShopResponse.class);
        mapper.setAddresses(addresses);
        return mapper;
    }

    @Override
    public ShopResponse createNewShopProfile(NewShopProfileRequest request) {
        var account = modelMapper.map(request, Account.class);
        account.setRole(Role.SHOP);
        account.setCreatedDate(Date.from(Instant.now()));
        account.setUpdatedDate(Date.from(Instant.now()));
        account.setShop(null);
        account.setAddresses(null);
        account = accountRepository.save(account);
        var shop = modelMapper.map(request, Shop.class);
        shop.setAccount(account);
        shopRepository.save(shop);
        var addressRequest = modelMapper.map(request.getAddress(), AddressRequest.class);
        var address = modelMapper.map(addressRequest, Address.class);
        addressService.save(address);
        return findShopByAccountId(request.getAccountId());
    }
}
