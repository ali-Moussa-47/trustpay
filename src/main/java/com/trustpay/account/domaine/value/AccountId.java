package com.trustpay.account.domaine.value;

import com.trustpay.account.domaine.exception.IdInvalidException;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

public record AccountId(String value) {
  public AccountId {
    validate(value);
  }

  private void validate(String value) {
    if (StringUtils.isBlank(value)) {
      throw new IdInvalidException("ID cannot be null or empty.");
    }
  }

  public static AccountId from(String raw) {
    return new AccountId(raw);
  }

  public static AccountId newId() {
    return new AccountId(UUID.randomUUID().toString());
  }
}
