package com.trustpay.account.domaine.exception;

public class InvalidPasswordHashException extends InvalidParameterException {

  public InvalidPasswordHashException(String description) {
    super(ErrorType.INVALID_PASSWORD, description);
  }
}
