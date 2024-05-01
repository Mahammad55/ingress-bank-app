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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.ACCOUNT_NOT_FOUND;
import static az.ingress.bankapp.enums.ExceptionMessage.CARD_NOT_FOUND;
import static az.ingress.bankapp.enums.ExceptionMessage.CARD_NUMBER_ALREADY_EXIST;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    private final AccountRepository accountRepository;

    private final CardMapper cardMapper;

    @Override
    public CardResponse getCardById(Long id) {
        log.info("CardService.getCardById.start id:{}", id);

        return cardRepository
                .findById(id)
                .map(cardMapper::entityToResponse)
                .orElseThrow(() -> {
                    log.error("CardService.getCardById.error -- card not found id:{}", id);
                    return new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(id));
                });
    }

    @Override
    public List<CardResponse> getAllCard() {
        log.info("CardService.getAllCard.start");

        List<CardResponse> cardResponseList = cardRepository
                .findAll()
                .stream()
                .map(cardMapper::entityToResponse)
                .toList();

        if (cardResponseList.isEmpty()) {
            log.info("CardService.getAllCard.error -- card not found");
            throw new NotFoundException(CARD_NOT_FOUND.getMessage());
        }

        return cardResponseList;
    }

    @Override
    public void saveCard(CardRequest cardRequest) {
        log.info("CardService.saveCard.start accountId:{} and cardNumber:{}", cardRequest.getAccountId(), cardRequest.getCardNumber());

        Long accountId = cardRequest.getAccountId();
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> {
                    log.error("CardService.saveCard.error -- account not found accountId:{}", accountId);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(accountId));
                });

        String cardNumber = cardRequest.getCardNumber();
        if (cardRepository.existsCardByCardNumber(cardNumber)) {
            log.error("CardService.saveCard.error -- card number already exist cardNumber:{}", cardNumber);
            throw new AlreadyExistException(CARD_NUMBER_ALREADY_EXIST.getMessage().formatted(cardNumber));
        }

        Card card = cardMapper.requestToEntity(cardRequest);
        card.setAccount(account);
        cardRepository.save(card);

        log.info("CardService.saveCard.success accountId:{} and cardNumber:{}", account, cardNumber);
    }

    @Override
    public void updateCard(Long id, CardRequest cardRequest) {
        log.info("CardService.updateCard.start id:{} and accountId:{} and cardNumber:{}", id, cardRequest.getAccountId(), cardRequest.getCardNumber());

        cardRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("CardService.updateCard.error -- card not found id:{}", id);
                    return new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(id));
                });

        Long accountId = cardRequest.getAccountId();
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> {
                    log.error("CardService.updateCard.error -- account not found accountId:{}", accountId);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(accountId));
                });

        Card card = cardMapper.requestToEntity(cardRequest);
        card.setId(id);
        card.setAccount(account);
        cardRepository.save(card);

        log.info("CardService.updateCard.success id:{} and accountId:{} and cardNumber:{}", id, accountId, cardRequest.getCardNumber());
    }


    @Override
    public void deleteCard(Long id) {
        log.info("CardService.deleteCard.start id:{}", id);

        cardRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("CardService.deleteCard.error card not found id:{}", id);
                    return new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(id));
                });

        cardRepository.deleteById(id);

        log.info("CardService.deleteCard.success id:{}", id);
    }
}
