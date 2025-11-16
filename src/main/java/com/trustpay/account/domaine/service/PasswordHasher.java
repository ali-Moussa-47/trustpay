package com.trustpay.account.domaine.service;

import com.trustpay.account.domaine.value.PasswordHash;
import com.trustpay.account.domaine.value.PlainPassword;

public interface PasswordHasher {
  PasswordHash hash(PlainPassword raw);

  boolean match(PlainPassword raw, PasswordHash hashed);
}
