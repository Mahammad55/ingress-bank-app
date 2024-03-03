package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.dto.response.AccountResponse;
import az.ingress.bankapp.entity.Account;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.AccountMapper;
import az.ingress.bankapp.repository.AccountRepository;
import az.ingress.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.ACCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountResponse getAccountById(Long accountId) {
        return accountRepository
                .findById(accountId)
                .map(accountMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(accountId)));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository
                .findAllByNamedQuery()
                .get();
    }
}
