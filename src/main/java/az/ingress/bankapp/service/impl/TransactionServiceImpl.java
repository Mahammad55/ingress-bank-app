package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.repository.TransactionRepository;
import az.ingress.bankapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
}
