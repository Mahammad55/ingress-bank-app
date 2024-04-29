package az.ingress.bankapp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    @Schema(type = "Long", example = "1")
    private Long id;

    private UserResponse user;

    @Schema(type = "String", example = "4163738895624253")
    private String accountNumber;

    @Schema(type = "Double", example = "500.00")
    private double balance;
}
