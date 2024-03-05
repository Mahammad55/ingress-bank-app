package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.dto.request.TransactionRequest;
import az.ingress.bankapp.dto.response.TransactionResponse;
import az.ingress.bankapp.entity.Account;
import az.ingress.bankapp.entity.Transaction;
import az.ingress.bankapp.enums.ExceptionMessage;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.TransactionMapper;
import az.ingress.bankapp.repository.AccountRepository;
import az.ingress.bankapp.repository.TransactionRepository;
import az.ingress.bankapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.ACCOUNT_NOT_FOUND;
import static az.ingress.bankapp.enums.ExceptionMessage.TRANSACTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponse getTransactionById(Long id) {
        return transactionRepository
                .findById(id)
                .map(transactionMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(TRANSACTION_NOT_FOUND.getMessage().formatted(id)));
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<TransactionResponse> transactionResponseList = transactionRepository
                .findAll()
                .stream()
                .map(transactionMapper::entityToResponse)
                .toList();

        if (transactionResponseList.isEmpty()) {
            throw new NotFoundException(TRANSACTION_NOT_FOUND.getMessage());
        }

        return transactionResponseList;
    }

    @Override
    public void saveTransaction(TransactionRequest transactionRequest) {
        Long fromAccountId = transactionRequest.getFromAccountId();
        Account fromAccount = accountRepository
                .findById(fromAccountId)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(fromAccountId)));

        Long toAccountId = transactionRequest.getToAccountId();
        Account toAccount = accountRepository
                .findById(toAccountId)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(toAccountId)));

        Transaction transaction = transactionMapper.requestToEntity(transactionRequest);
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transactionRepository.save(transaction);
    }

    @Override
    public void updateTransaction(Long id, TransactionRequest transactionRequest) {
        transactionRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TRANSACTION_NOT_FOUND.getMessage().formatted(id)));

        Long fromAccountId = transactionRequest.getFromAccountId();
        Account fromAccount = accountRepository
                .findById(fromAccountId)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(fromAccountId)));

        Long toAccountId = transactionRequest.getToAccountId();
        Account toAccount = accountRepository
                .findById(toAccountId)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(toAccountId)));

        Transaction transaction = transactionMapper.requestToEntity(transactionRequest);
        transaction.setId(id);
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TRANSACTION_NOT_FOUND.getMessage().formatted(id)));

        transactionRepository.deleteById(id);
    }
}
