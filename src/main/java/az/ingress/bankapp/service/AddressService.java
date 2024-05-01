package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.request.AddressRequest;
import az.ingress.bankapp.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse getAddressById(Long id);

    List<AddressResponse> getAllAddress();

    void saveAddress(AddressRequest addressRequest);

    void updateAddress(Long id, AddressRequest addressRequest);

    void deleteAddress(Long id);
}
