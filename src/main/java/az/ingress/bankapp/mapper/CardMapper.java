package az.ingress.bankapp.mapper;

import az.ingress.bankapp.dto.request.CardRequest;
import az.ingress.bankapp.dto.response.CardResponse;
import az.ingress.bankapp.entity.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardResponse entityToResponse(Card card);

    Card requestToEntity(CardRequest cardRequest);
}
