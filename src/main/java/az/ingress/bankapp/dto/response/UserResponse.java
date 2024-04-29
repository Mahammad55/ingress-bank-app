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
public class UserResponse {
    @Schema(type = "Long", example = "1")
    private Long id;

    @Schema(type = "String", example = "john")
    private String username;

    @Schema(type = "String", example = "password123")
    private String password;
}
