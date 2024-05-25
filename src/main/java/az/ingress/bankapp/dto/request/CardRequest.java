package az.ingress.bankapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    @NotNull(message = "account id must not be null")
    private Long accountId;

    @NotBlank(message = "card number must not be empty")
    private String cardNumber;

    @NotBlank(message = "card name must not be empty")
    private String cardName;

    @NotBlank(message = "expiration date must not be empty")
    @Pattern(regexp = "^\\d{4}/(0[1-9]|1[0-2])/([0-2][0-9]|3[0-1])$", message = "expiration date must be in the format YYYY/MM/dd")
    private String expirationDate;
}
