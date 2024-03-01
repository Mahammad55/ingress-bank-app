package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.repository.CardRepository;
import az.ingress.bankapp.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
}
