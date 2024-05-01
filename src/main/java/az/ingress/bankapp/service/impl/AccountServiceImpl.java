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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.bankapp.enums.ExceptionMessage.ACCOUNT_NOT_FOUND;
import static az.ingress.bankapp.enums.ExceptionMessage.ACCOUNT_NUMBER_ALREADY_EXIST;
import static az.ingress.bankapp.enums.ExceptionMessage.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountResponse getAccountById(Long id) {
        log.info("AccountService.getAccountById.start id:{}", id);

        return accountRepository
                .findById(id)
                .map(accountMapper::entityToResponse)
                .orElseThrow(() -> {
                    log.error("AccountService.getAccountById.error -- account not found id:{}", id);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(id));
                });
    }

    @Override
    public List<AccountResponse> getAllAccount() {
        log.info("AccountService.getAllAccount.start");

        List<AccountResponse> accountResponseList = accountRepository
                .findAll()
                .stream()
                .map(accountMapper::entityToResponse)
                .toList();

        if (accountResponseList.isEmpty()) {
            log.error("AccountService.getAllAccount.error -- account not found");
            throw new NotFoundException(ACCOUNT_NOT_FOUND.getMessage());
        }
        return accountResponseList;
    }

    @Override
    public void saveAccount(AccountRequest accountRequest) {
        log.info("AccountService.saveAccount.start userId:{} and accountNumber:{}", accountRequest.getUserId(), accountRequest.getAccountNumber());

        Long userId = accountRequest.getUserId();
        User user = userRepository
                .findUserById(userId)
                .orElseThrow(() -> {
                    log.error("AccountService.saveAccount.error -- user not found userId:{}", userId);
                    return new NotFoundException(USER_NOT_FOUND.getMessage().formatted(userId));
                });

        String accountNumber = accountRequest.getAccountNumber();
        if (accountRepository.existsAccountByAccountNumber(accountNumber)) {
            log.error("AccountService.saveAccount.error -- account number already exist accountNumber:{}", accountNumber);
            throw new AlreadyExistException(ACCOUNT_NUMBER_ALREADY_EXIST.getMessage().formatted(accountNumber));
        }

        Account account = accountMapper.requestToEntity(accountRequest);
        account.setUser(user);
        accountRepository.save(account);

        log.info("AccountService.saveAccount.success userId:{} and accountNumber:{}", userId, accountNumber);
    }

    @Override
    public void updateAccount(Long id, AccountRequest accountRequest) {
        log.info("AccountService.updateAccount.start id:{} and userId:{} and accountNumber:{}", id, accountRequest.getUserId(), accountRequest.getAccountNumber());

        accountRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("AccountService.updateAccount.error -- account not found id:{}", id);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(id));
                });

        Long userId = accountRequest.getUserId();
        User user = userRepository
                .findUserById(userId)
                .orElseThrow(() -> {
                    log.error("AccountService.updateAccount.error -- user not found userId:{}", userId);
                    return new NotFoundException(USER_NOT_FOUND.getMessage().formatted(userId));
                });

        Account account = accountMapper.requestToEntity(accountRequest);
        account.setId(id);
        account.setUser(user);
        accountRepository.save(account);

        log.info("AccountService.updateAccount.success id:{} and userId:{} and accountNumber:{}", id, userId, accountRequest.getAccountNumber());
    }

    @Override
    public void deleteAccount(Long id) {
        log.info("AccountService.deleteAccount.start id:{}", id);

        accountRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("AccountService.deleteAccount.error -- account not found id:{}", id);
                    return new NotFoundException(ACCOUNT_NOT_FOUND.getMessage().formatted(id));
                });

        accountRepository.deleteById(id);

        log.info("AccountService.deleteAccount.success id:{}", id);
    }
}
