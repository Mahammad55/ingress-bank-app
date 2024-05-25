package az.ingress.bankapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "account id must not be null")
    private Long fromAccountId;

    @NotNull(message = "account id must not be null")
    private Long toAccountId;

    @Positive(message = "amount must be positive")
    private double amount;

    @PastOrPresent(message = "date must be in the past or present")
    private LocalDate date;

    @NotBlank(message = "type must not be empty")
    private String type;
}
