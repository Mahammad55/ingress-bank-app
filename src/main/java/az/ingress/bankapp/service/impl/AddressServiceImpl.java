package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.dto.request.AddressRequest;
import az.ingress.bankapp.dto.response.AddressResponse;
import az.ingress.bankapp.entity.Address;
import az.ingress.bankapp.entity.User;
import az.ingress.bankapp.enums.ExceptionMessage;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.AddressMapper;
import az.ingress.bankapp.repository.AddressRepository;
import az.ingress.bankapp.repository.UserRepository;
import az.ingress.bankapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    private final AddressMapper addressMapper;

    @Override
    public AddressResponse getAddressById(Long id) {
        return addressRepository
                .findById(id)
                .map(addressMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND.getMessage().formatted(id)));
    }

    @Override
    public List<AddressResponse> getAllAddresses() {
        List<AddressResponse> addressResponseList = addressRepository
                .findAll()
                .stream()
                .map(addressMapper::entityToResponse)
                .toList();

        if (addressResponseList.isEmpty()) {
            throw new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND.getMessage());
        }

        return addressResponseList;
    }

    @Override
    public void saveAddress(AddressRequest addressRequest) {
        Long userId = addressRequest.getUserId();
        User user = userRepository
                .findUserById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage().formatted(userId)));

        Address address = addressMapper.requestToEntity(addressRequest);
        address.setUser(user);
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Long id, AddressRequest addressRequest) {
        addressRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND.getMessage().formatted(id)));

        Long userId = addressRequest.getUserId();
        User user = userRepository
                .findUserById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage().formatted(userId)));

        Address address = addressMapper.requestToEntity(addressRequest);
        address.setId(id);
        address.setUser(user);
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND.getMessage().formatted(id)));

        addressRepository.deleteById(id);
    }
}
