package com.trustpay.account.domaine.exception;

public class MissingPasswordException extends InvalidParameterException {

  public MissingPasswordException(String description) {
    super(ErrorType.MISSING_PASSWORD, description);
  }
}
