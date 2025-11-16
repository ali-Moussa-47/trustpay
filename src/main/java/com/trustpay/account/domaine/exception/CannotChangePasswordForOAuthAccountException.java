package com.trustpay.account.domaine.exception;

public class CannotChangePasswordForOAuthAccountException
  extends InvalidParameterException {

  public CannotChangePasswordForOAuthAccountException(String description) {
    super(ErrorType.PASSWORD_CHANGE_NOT_ALLOWED, description);
  }
}
