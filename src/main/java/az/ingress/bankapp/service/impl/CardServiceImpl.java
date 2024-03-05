package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.dto.request.CardRequest;
import az.ingress.bankapp.dto.response.CardResponse;
import az.ingress.bankapp.entity.Account;
import az.ingress.bankapp.entity.Card;
import az.ingress.bankapp.exception.AlreadyExistException;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.CardMapper;
import az.ingress.bankapp.repository.AccountRepository;
import az.ingress.bankapp.repository.CardRepository;
import az.ingress.bankapp.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    private final AccountRepository accountRepository;

    private final CardMapper cardMapper;

    @Override
    public CardResponse getCardById(Long id) {
        return cardRepository
                .findById(id)
                .map(cardMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(id)));
    }

    @Override
    public List<CardResponse> getAllCards() {
        List<CardResponse> cardResponseList = cardRepository
                .findAll()
                .stream()
                .map(cardMapper::entityToResponse)
                .toList();

        if (cardResponseList.isEmpty()) {
            throw new NotFoundException(CARD_NOT_FOUND.getMessage());
        }

        return cardResponseList;
    }

    @Override
    public void saveCard(CardRequest cardRequest) {
        Long accountId = cardRequest.getAccountId();
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(accountId)));

        String cardNumber = cardRequest.getCardNumber();
        if (cardRepository.existsCardByCardNumber(cardNumber)) {
            throw new AlreadyExistException(CARD_NUMBER_ALREADY_EXIST.getMessage().formatted(cardNumber));
        }

        Card card = cardMapper.requestToEntity(cardRequest);
        card.setAccount(account);
        cardRepository.save(card);
    }

    @Override
    public void updateCard(Long id, CardRequest cardRequest) {
        cardRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(id)));

        Long accountId = cardRequest.getAccountId();
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(accountId)));

        Card card = cardMapper.requestToEntity(cardRequest);
        card.setId(id);
        card.setAccount(account);
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(id)));

        cardRepository.deleteById(id);
    }
}
