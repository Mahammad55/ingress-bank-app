package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.dto.request.CardBenefitRequest;
import az.ingress.bankapp.dto.response.CardBenefitResponse;
import az.ingress.bankapp.entity.Card;
import az.ingress.bankapp.entity.CardBenefit;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.CardBenefitMapper;
import az.ingress.bankapp.repository.CardBenefitRepository;
import az.ingress.bankapp.repository.CardRepository;
import az.ingress.bankapp.service.CardBenefitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.CARD_BENEFIT_NOT_FOUND;
import static az.ingress.bankapp.enums.ExceptionMessage.CARD_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardBenefitServiceImpl implements CardBenefitService {
    private final CardBenefitRepository cardBenefitRepository;

    private final CardRepository cardRepository;

    private final CardBenefitMapper cardBenefitMapper;

    @Override
    public CardBenefitResponse getCardBenefitById(Long id) {
        log.info("CardBenefitService.getCardBenefitById.start id:{}", id);

        return cardBenefitRepository
                .findById(id)
                .map(cardBenefitMapper::entityToResponse)
                .orElseThrow(() -> {
                    log.error("CardBenefitService.getCardBenefitById.error -- card benefit not found id:{}", id);
                    return new NotFoundException(CARD_BENEFIT_NOT_FOUND.getMessage().formatted(id));
                });
    }

    @Override
    public List<CardBenefitResponse> getAllCardBenefit() {
        log.info("CardBenefitService.getAllCardBenefit.start");

        List<CardBenefitResponse> cardBenefitResponseList = cardBenefitRepository
                .findAll()
                .stream()
                .map(cardBenefitMapper::entityToResponse)
                .toList();

        if (cardBenefitResponseList.isEmpty()) {
            log.error("CardBenefitService.getAllCardBenefit.error -- card benefit not found");
            throw new NotFoundException(CARD_BENEFIT_NOT_FOUND.getMessage());
        }

        return cardBenefitResponseList;
    }

    @Override
    public void saveCardBenefit(CardBenefitRequest cardBenefitRequest) {
        log.info("CardBenefitService.saveCardBenefit.start cardId:{}", cardBenefitRequest.getCardId());

        Long cardId = cardBenefitRequest.getCardId();
        Card card = cardRepository
                .findById(cardId)
                .orElseThrow(() -> {
                    log.error("CardBenefitService.saveCardBenefit.error -- card not found cardId:{}", cardId);
                    return new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(cardId));
                });

        CardBenefit cardBenefit = cardBenefitMapper.requestToEntity(cardBenefitRequest);
        cardBenefit.setCard(card);
        cardBenefitRepository.save(cardBenefit);

        log.info("CardBenefitService.saveCardBenefit.success cardId:{}", cardId);
    }

    @Override
    public void updateCardBenefit(Long id, CardBenefitRequest cardBenefitRequest) {
        log.info("CardBenefitService.updateCardBenefit.start id:{} and cardId:{}", id, cardBenefitRequest.getCardId());

        cardBenefitRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("CardBenefitService.updateCardBenefit.error -- card benefit not found id:{}", id);
                    return new NotFoundException(CARD_BENEFIT_NOT_FOUND.getMessage().formatted(id));
                });

        Long cardId = cardBenefitRequest.getCardId();
        Card card = cardRepository
                .findById(cardId)
                .orElseThrow(() -> {
                    log.error("CardBenefitService.updateCardBenefit.error -- card not found cardId:{}", cardId);
                    return new NotFoundException(CARD_NOT_FOUND.getMessage().formatted(cardId));
                });

        CardBenefit cardBenefit = cardBenefitMapper.requestToEntity(cardBenefitRequest);
        cardBenefit.setId(id);
        cardBenefit.setCard(card);
        cardBenefitRepository.save(cardBenefit);

        log.info("CardBenefitService.updateCardBenefit.success id:{} and cardId:{}", id, cardId);
    }

    @Override
    public void deleteCardBenefit(Long id) {
        log.info("CardBenefitService.deleteCardBenefit.start id:{}", id);

        cardBenefitRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("CardBenefitService.deleteCardBenefit.error -- card benefit not found id:{}", id);
                    return new NotFoundException(CARD_BENEFIT_NOT_FOUND.getMessage().formatted(id));
                });

        cardBenefitRepository.deleteById(id);

        log.info("CardBenefitService.deleteCardBenefit.success id:{}", id);
    }
}
