package gigjob.service.impl;

import gigjob.entity.Address;
import gigjob.model.response.AddressResponse;
import gigjob.repository.AddressRepository;
import gigjob.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    public List<Address> findAddressesByAccountId(String id) {
        return addressRepository.findAddressByAccountId(id);
    }

    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<AddressResponse> getAddressResponsesFromAccountId(String id){
        var addresses = findAddressesByAccountId(id);
        return addresses.stream().map(a -> mapper.map(a, AddressResponse.class)).toList();
    }

    public AddressResponse getAddressResponseFromId(Long id) {
        var address = findAddressById(id);
        if (address != null) {
            return mapper.map(address, AddressResponse.class);
        }
        return null;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
