package com.trustpay.account.infrastructure.security;

import com.trustpay.account.domaine.service.PasswordHasher;
import com.trustpay.account.domaine.value.PasswordHash;
import com.trustpay.account.domaine.value.PlainPassword;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptPasswordHasher implements PasswordHasher {

  private final int strength;

  public BCryptPasswordHasher(int strength) {
    this.strength = strength;
  }

  @Override
  public PasswordHash hash(PlainPassword raw) {
    String hashed = BCrypt.hashpw(raw.value(), BCrypt.gensalt(strength));
    return new PasswordHash(hashed);
  }

  @Override
  public boolean match(PlainPassword raw, PasswordHash hashed) {
    return BCrypt.checkpw(raw.value(), hashed.value());
  }
}
