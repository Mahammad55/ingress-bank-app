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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    private final AddressMapper addressMapper;

    @Override
    public AddressResponse getAddressById(Long id) {
        log.info("AddressService.getCardBenefitById.start id:{}", id);

        return addressRepository
                .findById(id)
                .map(addressMapper::entityToResponse)
                .orElseThrow(() -> {
                    log.error("AddressService.getAddressById.error -- address not found id:{}", id);
                    return new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND.getMessage().formatted(id));
                });
    }

    @Override
    public List<AddressResponse> getAllAddress() {
        log.info("AddressService.getAllAddress.start");

        List<AddressResponse> addressResponseList = addressRepository
                .findAll()
                .stream()
                .map(addressMapper::entityToResponse)
                .toList();

        if (addressResponseList.isEmpty()) {
            log.error("AddressService.getAllAddress.error -- address not found");
            throw new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND.getMessage());
        }

        return addressResponseList;
    }

    @Override
    public void saveAddress(AddressRequest addressRequest) {
        log.info("AddressService.saveAddress.start userId:{}", addressRequest.getUserId());
        log.info("salam");

        Long userId = addressRequest.getUserId();
        User user = userRepository
                .findUserById(userId)
                .orElseThrow(() -> {
                    log.error("AddressService.saveAddress.error -- user not found userId:{}", userId);
                    return new NotFoundException(USER_NOT_FOUND.getMessage().formatted(userId));
                });

        Address address = addressMapper.requestToEntity(addressRequest);
        address.setUser(user);
        addressRepository.save(address);

        log.info("AddressService.saveAddress.success userId:{}", userId);
    }

    @Override
    public void updateAddress(Long id, AddressRequest addressRequest) {
        log.info("AddressService.updateAddress.start id:{} and userId:{}", id, addressRequest.getUserId());

        addressRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("AddressService.updateAddress.error -- address not found id:{}", id);
                    return new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND.getMessage().formatted(id));
                });

        Long userId = addressRequest.getUserId();
        User user = userRepository
                .findUserById(userId)
                .orElseThrow(() -> {
                    log.error("AddressService.updateAddress.error -- user not found userId:{}", userId);
                    return new NotFoundException(USER_NOT_FOUND.getMessage().formatted(userId));
                });

        Address address = addressMapper.requestToEntity(addressRequest);
        address.setId(id);
        address.setUser(user);
        addressRepository.save(address);

        log.info("AddressService.updateAddress.success id:{} and userId:{}", id, userId);
    }

    @Override
    public void deleteAddress(Long id) {
        log.info("AddressService.deleteAddress.start id:{}", id);

        addressRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("AddressService.deleteAddress.error -- address not found id:{}", id);
                    return new NotFoundException(ExceptionMessage.ADDRESS_NOT_FOUND.getMessage().formatted(id));
                });

        addressRepository.deleteById(id);

        log.info("AddressService.deleteAddress.success id:{}", id);
    }
}
