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
    @Schema(name = "id", type = "Long", example = "1", description = "Id of Account")
    private Long id;

    private UserResponse user;

    @Schema(name = "accountNumber", type = "String", example = "4163738895624253", description = "AccountNumber of Account")
    private String accountNumber;

    @Schema(name = "balance", type = "Double", example = "500.00", description = "Balance of Account")
    private double balance;
}
