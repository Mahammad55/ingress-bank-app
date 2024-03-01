package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.repository.AddressRepository;
import az.ingress.bankapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
}
