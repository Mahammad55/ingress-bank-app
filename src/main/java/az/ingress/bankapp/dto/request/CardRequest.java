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
    private Long accountId;

    private String cardNumber;

    private String cardName;

    private String expirationDate;
}
