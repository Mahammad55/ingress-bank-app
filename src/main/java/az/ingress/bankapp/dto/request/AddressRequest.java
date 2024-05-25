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
public class AddressRequest {
    @NotBlank(message = "street must not be empty")
    private String street;

    @NotBlank(message = "city must not be empty")
    private String city;

    @NotBlank(message = "postal code must not be empty")
    private String postalCode;

    @NotNull(message = "user id must not be null")
    private Long userId;
}
