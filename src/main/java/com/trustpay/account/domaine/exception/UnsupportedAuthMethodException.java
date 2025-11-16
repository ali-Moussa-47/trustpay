package com.trustpay.account.domaine.exception;

public class UnsupportedAuthMethodException extends InvalidParameterException {

  public UnsupportedAuthMethodException(String description) {
    super(ErrorType.UNSUPPORTED_AUTH_METHOD, description);
  }
}
