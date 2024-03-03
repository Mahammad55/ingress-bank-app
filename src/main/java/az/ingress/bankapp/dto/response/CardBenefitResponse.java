package az.ingress.bankapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardBenefitResponse {
    private Long id;

    private String name;

    private String description;

    private CardResponse card;
}
