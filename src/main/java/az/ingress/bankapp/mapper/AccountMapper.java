package az.ingress.bankapp.mapper;

import az.ingress.bankapp.dto.request.AccountRequest;
import az.ingress.bankapp.dto.response.AccountResponse;
import az.ingress.bankapp.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponse entityToResponse(Account account);

    Account requestToEntity(AccountRequest accountRequest);
}
