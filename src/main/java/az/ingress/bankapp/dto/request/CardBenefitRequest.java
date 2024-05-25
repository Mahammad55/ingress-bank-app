package az.ingress.bankapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardBenefitRequest {
    @NotBlank(message = "card benefit name must not be empty")
    private String name;

    @NotBlank(message = "card benefit description must not be empty")
    private String description;

    @NotNull(message = "card id must not be null")
    private Long cardId;
}
