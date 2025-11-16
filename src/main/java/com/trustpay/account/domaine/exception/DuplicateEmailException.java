package com.trustpay.account.domaine.exception;

public class DuplicateEmailException extends InvalidParameterException {

  public DuplicateEmailException(String description) {
    super(ErrorType.DUPLICATE_EMAIL, description);
  }
}
