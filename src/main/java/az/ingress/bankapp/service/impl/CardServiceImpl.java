package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.entity.Card;
import az.ingress.bankapp.repository.CardRepository;
import az.ingress.bankapp.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public List<Card> findAll() {
        return cardRepository.findAllCards().get();
    }
}
