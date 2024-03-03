package az.ingress.bankapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    private Long id;

    private AccountRequest account;

    private String cardNumber;

    private String cardName;

    private String expirationDate;
}
