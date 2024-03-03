package az.ingress.bankapp.controller;

import az.ingress.bankapp.dto.response.AccountResponse;
import az.ingress.bankapp.entity.Account;
import az.ingress.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable("id") Long accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}