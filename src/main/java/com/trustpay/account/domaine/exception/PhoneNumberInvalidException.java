package com.trustpay.account.domaine.exception;

public class PhoneNumberInvalidException extends InvalidParameterException {

  public PhoneNumberInvalidException(String description) {
    super(ErrorType.INVALID_PHONE_NUMBER, description);
  }
}
