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
    @Schema(name = "id", type = "Long", example = "1", description = "Id of User", hidden = true)
    private Long id;

    @Schema(name = "username", type = "String", example = "john", description = "Username of User")
    private String username;

    @Schema(name = "password", type = "String", example = "password123", description = "Password of User")
    private String password;
}
