package com.trustpay.account.domaine.factory;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.service.PasswordHasher;
import com.trustpay.account.domaine.value.*;
import java.util.Set;

public class LocalAccountCreationStrategy implements AccountCreationStrategy {

  private final PasswordHasher hasher;

  public LocalAccountCreationStrategy(PasswordHasher hasher) {
    this.hasher = hasher;
  }

  @Override
  public Account create(
    AccountId id,
    Email email,
    PhoneNumber phone,
    PlainPassword password
  ) {
    PasswordHash hash = hasher.hash(password);
    return new Account(
      id,
      email,
      phone,
      AuthProvider.LOCAL,
      Set.of(AuthMethod.PASSWORD),
      hash
    );
  }
}
