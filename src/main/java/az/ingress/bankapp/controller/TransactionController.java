package az.ingress.bankapp.controller;

import az.ingress.bankapp.dto.request.TransactionRequest;
import az.ingress.bankapp.dto.response.TransactionResponse;
import az.ingress.bankapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionResponse>> getAllTransaction() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PostMapping("/")
    @ResponseStatus(CREATED)
    public void saveTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionService.saveTransaction(transactionRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest) {
        transactionService.updateTransaction(id, transactionRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
