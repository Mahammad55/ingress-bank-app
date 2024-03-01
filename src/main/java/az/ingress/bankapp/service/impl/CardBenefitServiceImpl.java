package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.repository.CardBenefitRepository;
import az.ingress.bankapp.service.CardBenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardBenefitServiceImpl implements CardBenefitService {
    private final CardBenefitRepository cardBenefitRepository;
}
