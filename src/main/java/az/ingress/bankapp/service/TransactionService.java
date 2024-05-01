package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.request.TransactionRequest;
import az.ingress.bankapp.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse getTransactionById(Long id);

    List<TransactionResponse> getAllTransaction();

    void saveTransaction(TransactionRequest transactionRequest);

    void updateTransaction(Long id, TransactionRequest transactionRequest);

    void deleteTransaction(Long id);
}
