package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.request.CardBenefitRequest;
import az.ingress.bankapp.dto.response.CardBenefitResponse;

import java.util.List;

public interface CardBenefitService  {
    CardBenefitResponse getCardBenefitById(Long id);

    List<CardBenefitResponse> getAllCardBenefit();

    void saveCardBenefit(CardBenefitRequest cardBenefitRequest);

    void updateCardBenefit(Long id, CardBenefitRequest cardBenefitRequest);

    void deleteCardBenefit(Long id);
}
