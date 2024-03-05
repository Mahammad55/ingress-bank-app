package az.ingress.bankapp.service.impl;

import az.ingress.bankapp.dto.request.AccountRequest;
import az.ingress.bankapp.dto.response.AccountResponse;
import az.ingress.bankapp.entity.Account;
import az.ingress.bankapp.entity.User;
import az.ingress.bankapp.exception.AlreadyExistException;
import az.ingress.bankapp.exception.NotFoundException;
import az.ingress.bankapp.mapper.AccountMapper;
import az.ingress.bankapp.repository.AccountRepository;
import az.ingress.bankapp.repository.UserRepository;
import az.ingress.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountResponse getAccountById(Long id) {
        return accountRepository
                .findById(id)
                .map(accountMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(id)));
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        List<AccountResponse> accountResponseList = accountRepository
                .findAll()
                .stream()
                .map(accountMapper::entityToResponse)
                .toList();

        if (accountResponseList.isEmpty()) {
            throw new NotFoundException(ACCOUNT_NOT_FOUND.getMessage());
        }
        return accountResponseList;
    }

    @Override
    public void saveAccount(AccountRequest accountRequest) {
        Long userId = accountRequest.getUserId();
        User user = userRepository
                .findUserById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage().formatted(userId)));

        String accountNumber = accountRequest.getAccountNumber();
        if (accountRepository.existsAccountByAccountNumber(accountNumber)) {
            throw new AlreadyExistException(ACCOUNT_NUMBER_ALREADY_EXIST.getMessage().formatted(accountNumber));
        }

        Account account = accountMapper.requestToEntity(accountRequest);
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(Long id, AccountRequest accountRequest) {
        accountRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(id)));

        Long userId = accountRequest.getUserId();
        User user = userRepository
                .findUserById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage().formatted(userId)));

        Account account = accountMapper.requestToEntity(accountRequest);
        account.setId(id);
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(id)));

        accountRepository.deleteById(id);
    }
}
