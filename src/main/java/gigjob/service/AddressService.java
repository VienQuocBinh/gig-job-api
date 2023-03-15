package gigjob.service;

import gigjob.entity.Address;
import gigjob.model.response.AddressResponse;

import java.util.List;

public interface AddressService {
    Address findAddressById(Long id);
    List<Address> findAddressesByAccountId(String id);

    List<AddressResponse> getAddressResponsesFromAccountId(String id);

    AddressResponse getAddressResponseFromId(Long id);

    Address save(Address address);
}