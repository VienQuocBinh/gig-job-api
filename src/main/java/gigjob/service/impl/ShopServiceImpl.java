package gigjob.service.impl;

import gigjob.common.meta.Role;
import gigjob.entity.Account;
import gigjob.entity.Address;
import gigjob.entity.Shop;
import gigjob.entity.Wallet;
import gigjob.model.request.AddressRequest;
import gigjob.model.request.NewShopProfileRequest;
import gigjob.model.request.ShopRequest;
import gigjob.model.response.ShopResponse;
import gigjob.repository.AccountRepository;
import gigjob.repository.ShopRepository;
import gigjob.repository.WalletRepository;
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
    private WalletRepository walletRepository;

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
        address.setAccount(account);
        walletRepository.save(Wallet.builder().account(account).balance(0.0).build());
        addressService.save(address);
        return findShopByAccountId(request.getAccountId());
    }

    @Override
    public ShopResponse updateShopProfile(ShopRequest shopRequest) {
        var account = accountRepository.findById(shopRequest.getAccountId()).orElseThrow(() ->new NotFoundException("Account not found"));
        account.setPhone(shopRequest.getPhone());
        account.setUsername(shopRequest.getUsername());
        account.setUsername(shopRequest.getUsername());
        if (shopRequest.getImageUrl() != null) account.setImageUrl(shopRequest.getImageUrl());
        accountRepository.save(account);
        var shop = shopRepository.findByAccountId(account.getId()).orElseThrow(() -> new NotFoundException("Shop not found"));
        shop.setName(shopRequest.getName());
        shop.setDescription(shopRequest.getDescription());
        shopRepository.save(shop);
        var address = addressService.findAddressesByAccountId(shopRequest.getAccountId());
        if (address == null || address.isEmpty()) {
            var newAddress = modelMapper.map(shopRequest.getAddress(), Address.class);
            newAddress.setAccount(account);
            addressService.save(newAddress);
        } else {
            var editAddress = address.get(0);
            editAddress.setStreet(shopRequest.getAddress().getStreet());
            editAddress.setDistrict(shopRequest.getAddress().getDistrict());
            editAddress.setCity(shopRequest.getAddress().getCity());
            editAddress.setProvince(shopRequest.getAddress().getProvince());
            editAddress.setCountry(shopRequest.getAddress().getCountry());
            addressService.save(editAddress);
        }
        return findShopByAccountId(account.getId());
    }
}
