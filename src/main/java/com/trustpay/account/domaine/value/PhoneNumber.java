package com.trustpay.account.domaine.value;

import com.trustpay.account.domaine.exception.PhoneNumberInvalidException;
import org.apache.commons.lang3.StringUtils;

public record PhoneNumber(String value) {
  public PhoneNumber {
    validate(value);
  }

  private void validate(String value) {
    if (StringUtils.isBlank(value)) {
      throw new PhoneNumberInvalidException("Phone number cannot be null or empty.");
    }
  }

  public static PhoneNumber from(String value) {
    return new PhoneNumber(value);
  }
}
