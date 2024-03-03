package az.ingress.bankapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long id;

    private UserResponse user;

    private String accountNumber;

    private double balance;
}
