package az.ingress.bankapp.mapper;

import az.ingress.bankapp.dto.request.TransactionRequest;
import az.ingress.bankapp.dto.response.TransactionResponse;
import az.ingress.bankapp.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse entityToResponse(Transaction transaction);

    Transaction requestToEntity(TransactionRequest transactionRequest);
}
