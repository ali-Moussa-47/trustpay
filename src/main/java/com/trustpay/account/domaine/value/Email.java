package com.trustpay.account.domaine.value;

import com.trustpay.account.domaine.exception.EmailInvalidException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public record Email(String email) {
  public Email {
    validate(email);
  }

  private void validate(String email) {
    if (StringUtils.isBlank(email)) {
      throw new EmailInvalidException("Email cannot be null or empty");
    }

    if (!EmailValidator.getInstance().isValid(email)) {
      throw new EmailInvalidException("Invalid email format");
    }
  }

  public static Email from(String value) {
    return new Email(value);
  }
}
