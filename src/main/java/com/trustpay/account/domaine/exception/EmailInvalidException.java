package com.trustpay.account.domaine.exception;

public class EmailInvalidException extends InvalidParameterException {

  public EmailInvalidException(String description) {
    super(ErrorType.INVALID_EMAIL, description);
  }
}
