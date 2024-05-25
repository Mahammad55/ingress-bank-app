package az.ingress.bankapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    @NotNull(message = "user id must not be null")
    private Long userId;

    @NotBlank(message = "account number must not be empty")
    @Pattern(regexp = "[0-9]+", message = "account number must contain only 0-9 numbers characters")
    private String accountNumber;

    @PositiveOrZero(message = "balance must be zero or positive")
    private double balance;
}
