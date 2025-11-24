package com.trustpay.account.domaine.service;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.exception.InvalidPasswordHashException;
import com.trustpay.account.domaine.exception.UnsupportedAuthMethodException;
import com.trustpay.account.domaine.value.AuthMethod;
import com.trustpay.account.domaine.value.PasswordHash;
import com.trustpay.account.domaine.value.PlainPassword;

public class AccountPasswordService {

  private final PasswordHasher hasher;

  public AccountPasswordService(PasswordHasher hasher) {
    this.hasher = hasher;
  }

  public boolean authenticate(Account account, PlainPassword rawPassword) {
    if (!account.supports(AuthMethod.PASSWORD)) {
      throw new UnsupportedAuthMethodException(
        "This account does not support password authentication"
      );
    }

    PasswordHash hash = account.getPasswordHash();
    if (hash == null) {
      throw new InvalidPasswordHashException("Missing password hash for account");
    }

    return hasher.match(rawPassword, hash);
  }

  public void changePassword(Account account, PlainPassword newPassword) {
    if (!account.supports(AuthMethod.PASSWORD)) {
      throw new UnsupportedAuthMethodException(
        "This account does not support password authentication"
      );
    }

    PasswordHash newHash = hasher.hash(newPassword);
    account.changePasswordHash(newHash);
  }
}
