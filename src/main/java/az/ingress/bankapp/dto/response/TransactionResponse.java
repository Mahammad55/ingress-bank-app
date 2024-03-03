package az.ingress.bankapp.dto.response;

import az.ingress.bankapp.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long id;

    private AccountResponse fromAccount;

    private AccountResponse toAccount;

    private double amount;

    private LocalDate date;

    private String type;
}
