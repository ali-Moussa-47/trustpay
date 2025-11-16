package com.trustpay.account.domaine.exception;

public class PasswordInvalidException extends InvalidParameterException {

  public PasswordInvalidException(String description) {
    super(ErrorType.INVALID_PASSWORD, description);
  }
}
