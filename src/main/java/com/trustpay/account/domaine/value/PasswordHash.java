package com.trustpay.account.domaine.value;

import com.trustpay.account.domaine.exception.InvalidPasswordHashException;
import org.apache.commons.lang3.StringUtils;

public record PasswordHash(String value) {
  public PasswordHash {
    if (StringUtils.isBlank(value)) {
      throw new InvalidPasswordHashException("PasswordHash cannot be null or empty");
    }
  }

  public static PasswordHash from(String value) {
    return new PasswordHash(value);
  }
}
