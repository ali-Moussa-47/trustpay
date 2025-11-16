package com.trustpay.account.domaine.exception;

public class CannotAuthenticateOAuthAccountException extends InvalidParameterException {

  public CannotAuthenticateOAuthAccountException(String description) {
    super(ErrorType.AUTHENTICATION_NOT_ALLOWED, description);
  }
}
