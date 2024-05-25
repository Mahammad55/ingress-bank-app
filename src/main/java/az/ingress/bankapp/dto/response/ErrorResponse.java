package az.ingress.bankapp.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String timestamp;

    private Integer status;

    private String message;

    private String path;
}