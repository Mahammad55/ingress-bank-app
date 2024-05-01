package az.ingress.bankapp.mapper;

import az.ingress.bankapp.dto.request.CardRequest;
import az.ingress.bankapp.dto.response.CardResponse;
import az.ingress.bankapp.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    CardResponse entityToResponse(Card card);

    Card requestToEntity(CardRequest cardRequest);
}
