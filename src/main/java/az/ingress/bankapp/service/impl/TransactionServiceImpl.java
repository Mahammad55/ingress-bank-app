package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.dto.request.TransactionRequest;
import az.ingress.bankapp.dto.response.TransactionResponse;
import az.ingress.bankapp.entity.Account;
import az.ingress.bankapp.entity.Transaction;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.TransactionMapper;
import az.ingress.bankapp.repository.AccountRepository;
import az.ingress.bankapp.repository.TransactionRepository;
import az.ingress.bankapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.ACCOUNT_NOT_FOUND;
import static az.ingress.bankapp.enums.ExceptionMessage.TRANSACTION_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponse getTransactionById(Long id) {
        log.info("TransactionService.getTransactionById.start id:{}", id);

        return transactionRepository
                .findById(id)
                .map(transactionMapper::entityToResponse)
                .orElseThrow(() -> {
                    log.error("TransactionService.getTransactionById.error -- transaction not found id:{}", id);
                    return new NotFoundException(TRANSACTION_NOT_FOUND.getMessage().formatted(id));
                });
    }

    @Override
    public List<TransactionResponse> getAllTransaction() {
        log.info("TransactionService.getAllTransaction.start");

        List<TransactionResponse> transactionResponseList = transactionRepository
                .findAll()
                .stream()
                .map(transactionMapper::entityToResponse)
                .toList();

        if (transactionResponseList.isEmpty()) {
            log.error("TransactionService.getAllTransaction.error -- transaction not found");
            throw new NotFoundException(TRANSACTION_NOT_FOUND.getMessage());
        }

        return transactionResponseList;
    }

    @Override
    public void saveTransaction(TransactionRequest transactionRequest) {
        log.info("TransactionService.saveTransaction.start fromAccountId:{} and toAccountId:{}", transactionRequest.getFromAccountId(), transactionRequest.getToAccountId());

        Long fromAccountId = transactionRequest.getFromAccountId();
        Account fromAccount = accountRepository
                .findById(fromAccountId)
                .orElseThrow(() -> {
                    log.error("TransactionService.saveTransaction.error -- account not found fromAccountId:{}", fromAccountId);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(fromAccountId));
                });

        Long toAccountId = transactionRequest.getToAccountId();
        Account toAccount = accountRepository
                .findById(toAccountId)
                .orElseThrow(() -> {
                    log.error("TransactionService.saveTransaction.error -- account not found toAccountId:{}", toAccountId);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(toAccountId));
                });

        Transaction transaction = transactionMapper.requestToEntity(transactionRequest);
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transactionRepository.save(transaction);

        log.info("TransactionService.saveTransaction.success fromAccountId:{} and toAccountId:{}", fromAccountId, toAccountId);
    }

    @Override
    public void updateTransaction(Long id, TransactionRequest transactionRequest) {
        log.info("TransactionService.updateTransaction.start id:{} and fromAccountId:{} and toAccountId:{}", id, transactionRequest.getFromAccountId(), transactionRequest.getToAccountId());

        transactionRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("TransactionService.updateTransaction.error -- transaction not found id:{}", id);
                    return new NotFoundException(TRANSACTION_NOT_FOUND.getMessage().formatted(id));
                });

        Long fromAccountId = transactionRequest.getFromAccountId();
        Account fromAccount = accountRepository
                .findById(fromAccountId)
                .orElseThrow(() -> {
                    log.error("TransactionService.updateTransaction.error -- account not found fromAccountId:{}", fromAccountId);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(fromAccountId));
                });

        Long toAccountId = transactionRequest.getToAccountId();
        Account toAccount = accountRepository
                .findById(toAccountId)
                .orElseThrow(() -> {
                    log.error("TransactionService.updateTransaction.error -- account not found toAccountId:{}", toAccountId);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(toAccountId));
                });

        Transaction transaction = transactionMapper.requestToEntity(transactionRequest);
        transaction.setId(id);
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transactionRepository.save(transaction);

        log.info("TransactionService.updateTransaction.success id:{} and fromAccountId:{} and toAccountId:{}", id, fromAccount, toAccountId);
    }

    @Override
    public void deleteTransaction(Long id) {
        log.info("TransactionService.deleteTransaction.start id:{}", id);

        transactionRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("TransactionService.deleteTransaction.error -- transaction not found id:{}", id);
                    return new NotFoundException(TRANSACTION_NOT_FOUND.getMessage().formatted(id));
                });

        transactionRepository.deleteById(id);

        log.info("TransactionService.deleteTransaction.success id:{}", id);
    }
}
