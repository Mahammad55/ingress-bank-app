package az.ingress.bankapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    USER_NOT_FOUND("User by parameter=%s not found"),

    ACCOUNT_NOT_FOUND("Account by parameter=%s not found");

    private final String message;
}