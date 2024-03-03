package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.response.AccountResponse;
import az.ingress.bankapp.entity.Account;

import java.util.List;

public interface AccountService {
    AccountResponse getAccountById(Long accountId);

    List<Account> getAllAccounts();
}
