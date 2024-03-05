package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.request.CardRequest;
import az.ingress.bankapp.dto.response.CardResponse;

import java.util.List;

public interface CardService {
    CardResponse getCardById(Long id);

    List<CardResponse> getAllCards();

    void saveCard(CardRequest CardRequest);

    void updateCard(Long id, CardRequest cardRequest);

    void deleteCard(Long id);
}
