package az.ingress.bankapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
    private Long id;

    private AccountResponse account;

    private String cardNumber;

    private String cardName;

    private String expirationDate;
}
