package com.trustpay.account.domaine.exception;

import lombok.Getter;

@Getter
public abstract class InvalidParameterException extends RuntimeException {

  private final ErrorType errorType;

  public InvalidParameterException(ErrorType errorType, String description) {
    super(description);
    this.errorType = errorType;
  }
}
