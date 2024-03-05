package az.ingress.bankapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    USER_NOT_FOUND("User by parameter=%s not found"),

    CARD_NOT_FOUND("Card by parameter=%s not found"),

    CARD_NUMBER_ALREADY_EXIST("Card number by parameter=%s already exist"),

    CARD_BENEFIT_NOT_FOUND("Card benefit by parameter=%s not found"),

    ACCOUNT_NOT_FOUND("Account by parameter=%s not found"),

    ACCOUNT_NUMBER_ALREADY_EXIST("Account number by parameter=%s already exist"),

    ADDRESS_NOT_FOUND("Address by parameter=%s not found"),

    TRANSACTION_NOT_FOUND("Transaction by parameter=%s not found");

    private final String message;
}