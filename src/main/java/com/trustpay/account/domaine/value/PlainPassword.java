package com.trustpay.account.domaine.value;

import com.trustpay.account.domaine.exception.PasswordInvalidException;
import org.apache.commons.lang3.StringUtils;

public record PlainPassword(String value) {
  public PlainPassword {
    validate(value);
  }

  private void validate(String value) {
    if (StringUtils.isBlank(value)) {
      throw new PasswordInvalidException("Password cannot be null or empty");
    }

    if (value.length() < 8) {
      throw new PasswordInvalidException("Password must be at least 8 characters long");
    }
  }

  public static PlainPassword from(String value) {
    return new PlainPassword(value);
  }
}
