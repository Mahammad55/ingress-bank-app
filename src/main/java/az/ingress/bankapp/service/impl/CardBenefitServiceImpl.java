package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.dto.request.CardBenefitRequest;
import az.ingress.bankapp.dto.response.CardBenefitResponse;
import az.ingress.bankapp.entity.Card;
import az.ingress.bankapp.entity.CardBenefit;
import az.ingress.bankapp.enums.ExceptionMessage;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.CardBenefitMapper;
import az.ingress.bankapp.repository.CardBenefitRepository;
import az.ingress.bankapp.repository.CardRepository;
import az.ingress.bankapp.service.CardBenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.CARD_BENEFIT_NOT_FOUND;
import static az.ingress.bankapp.enums.ExceptionMessage.CARD_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CardBenefitServiceImpl implements CardBenefitService {
    private final CardBenefitRepository cardBenefitRepository;

    private final CardRepository cardRepository;

    private final CardBenefitMapper cardBenefitMapper;

    @Override
    public CardBenefitResponse getCardBenefitById(Long id) {
        return cardBenefitRepository
                .findById(id)
                .map(cardBenefitMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(CARD_BENEFIT_NOT_FOUND.getMessage().formatted(id)));
    }

    @Override
    public List<CardBenefitResponse> getAllCardBenefits() {
        List<CardBenefitResponse> cardBenefitResponseList = cardBenefitRepository
                .findAll()
                .stream()
                .map(cardBenefitMapper::entityToResponse)
                .toList();

        if (cardBenefitResponseList.isEmpty()) {
            throw new NotFoundException(CARD_BENEFIT_NOT_FOUND.getMessage());
        }

        return cardBenefitResponseList;
    }

    @Override
    public void saveCardBenefit(CardBenefitRequest cardBenefitRequest) {
        Long cardId = cardBenefitRequest.getCardId();
        Card card = cardRepository
                .findById(cardId)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(cardId)));

        CardBenefit cardBenefit = cardBenefitMapper.requestToEntity(cardBenefitRequest);
        cardBenefit.setCard(card);
        cardBenefitRepository.save(cardBenefit);
    }

    @Override
    public void updateCardBenefit(Long id, CardBenefitRequest cardBenefitRequest) {
        cardBenefitRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(CARD_BENEFIT_NOT_FOUND.getMessage().formatted(id)));

        Long cardId = cardBenefitRequest.getCardId();
        Card card = cardRepository
                .findById(cardId)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(cardId)));

        CardBenefit cardBenefit = cardBenefitMapper.requestToEntity(cardBenefitRequest);
        cardBenefit.setId(id);
        cardBenefit.setCard(card);
        cardBenefitRepository.save(cardBenefit);
    }

    @Override
    public void deleteCardBenefit(Long id) {
        cardBenefitRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(CARD_BENEFIT_NOT_FOUND.getMessage().formatted(id)));

        cardBenefitRepository.deleteById(id);
    }
}
