package com.trustpay.account.domaine.exception;

public class DuplicatePhoneNumberException extends InvalidParameterException {

  public DuplicatePhoneNumberException(String description) {
    super(ErrorType.DUPLICATE_PHONE_NUMBER, description);
  }
}
