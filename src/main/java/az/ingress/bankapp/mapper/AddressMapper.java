package az.ingress.bankapp.mapper;

import az.ingress.bankapp.dto.request.AddressRequest;
import az.ingress.bankapp.dto.response.AddressResponse;
import az.ingress.bankapp.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    AddressResponse entityToResponse(Address address);

    Address requestToEntity(AddressRequest addressRequest);
}
