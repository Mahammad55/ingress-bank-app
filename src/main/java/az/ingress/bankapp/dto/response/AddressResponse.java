package az.ingress.bankapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Long id;

    private String street;

    private String city;

    private String postalCode;

    private UserResponse user;
}
