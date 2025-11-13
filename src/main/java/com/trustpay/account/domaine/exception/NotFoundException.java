package com.trustpay.account.domaine.exception;

import lombok.Getter;

@Getter
public abstract class NotFoundException extends RuntimeException {
    private final ErrorType errorType;

    public NotFoundException(ErrorType errorType, String description) {
        super(description);
        this.errorType = errorType;
    }
}
