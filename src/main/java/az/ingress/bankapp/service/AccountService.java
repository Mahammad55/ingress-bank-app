package az.ingress.bankapp.service;

import az.ingress.bankapp.dto.request.AccountRequest;
import az.ingress.bankapp.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse getAccountById(Long id);

    List<AccountResponse> getAllAccount();

    void saveAccount(AccountRequest accountRequest);

    void updateAccount(Long id, AccountRequest accountRequest);

    void deleteAccount(Long id);
}
