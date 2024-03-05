package az.ingress.bankapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Long fromAccountId;

    private Long toAccountId;

    private double amount;

    private LocalDate date;

    private String type;
}
