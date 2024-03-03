package az.ingress.bankapp.mapper;

import az.ingress.bankapp.dto.request.AddressRequest;
import az.ingress.bankapp.dto.response.AddressResponse;
import az.ingress.bankapp.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponse entityToResponse(Address address);

    Address requestToEntity(AddressRequest addressRequest);
}
