package az.ingress.bankapp.service;

import az.ingress.bankapp.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> findAll();
}
