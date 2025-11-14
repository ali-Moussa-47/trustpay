package com.trustpay.account.domaine.exception;

public class IdInvalidException extends InvalidParameterException {

    public IdInvalidException(String description) {
        super(ErrorType.INVALID_ID, description);
    }
}
