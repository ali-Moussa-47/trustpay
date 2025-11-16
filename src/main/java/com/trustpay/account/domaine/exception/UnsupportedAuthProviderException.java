package com.trustpay.account.domaine.exception;

public class UnsupportedAuthProviderException extends InvalidParameterException {

  public UnsupportedAuthProviderException(String description) {
    super(ErrorType.UNSUPPORTED_AUTH_PROVIDER, description);
  }
}
