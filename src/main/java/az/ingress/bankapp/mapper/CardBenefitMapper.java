package az.ingress.bankapp.mapper;

import az.ingress.bankapp.dto.request.CardBenefitRequest;
import az.ingress.bankapp.dto.response.CardBenefitResponse;
import az.ingress.bankapp.entity.CardBenefit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardBenefitMapper {
    CardBenefitResponse entityToResponse(CardBenefit cardBenefit);

    CardBenefit requestToEntity(CardBenefitRequest cardBenefitRequest);
}
