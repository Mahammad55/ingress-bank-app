package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.request.AccountRequest;
import az.ingress.bankapp.dto.response.AccountResponse;
import az.ingress.bankapp.entity.Account;

import java.util.List;

public interface AccountService {
    AccountResponse getAccountById(Long id);

    List<AccountResponse> getAllAccounts();

    void saveAccount(AccountRequest accountRequest);

    void updateAccount(Long id, AccountRequest accountRequest);

    void deleteAccount(Long id);
}
