package com.trustpay.account.domaine.entity;

import com.trustpay.account.domaine.exception.*;
import com.trustpay.account.domaine.service.PasswordHasher;
import com.trustpay.account.domaine.value.*;
import java.util.Set;

public class Account {

  private final AccountId accountId;
  private final Email email;
  private final PhoneNumber phoneNumber;
  private final AuthProvider authProvider;

  /**
   * Liste des méthodes d'authentification supportées par ce compte.
   * Ex : [PASSWORD] ou [OAUTH] ou [PASSWORD, OTP]
   */
  private final Set<AuthMethod> supportedMethods;

  private PasswordHash passwordHash;

  public Account(
    AccountId accountId,
    Email email,
    PhoneNumber phoneNumber,
    AuthProvider authProvider,
    Set<AuthMethod> supportedMethods,
    PasswordHash passwordHash
  ) {
    this.accountId = accountId;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.authProvider = authProvider;
    this.supportedMethods = supportedMethods;
    this.passwordHash = passwordHash;
  }

  public boolean supports(AuthMethod method) {
    return supportedMethods.contains(method);
  }

  public boolean authenticate(PasswordHasher hasher, PlainPassword rawPassword) {
    if (!supports(AuthMethod.PASSWORD)) {
      throw new UnsupportedAuthMethodException(
        "This account does not support password authentication"
      );
    }

    if (passwordHash == null) {
      throw new InvalidPasswordHashException("Missing password hash for account");
    }

    return hasher.match(rawPassword, passwordHash);
  }

  public void changePassword(PasswordHasher hasher, PlainPassword newPassword) {
    if (!supports(AuthMethod.PASSWORD)) {
      throw new CannotChangePasswordForOAuthAccountException(
        "This account cannot change password because it does not support PASSWORD method"
      );
    }

    this.passwordHash = hasher.hash(newPassword);
  }

  public AccountId getAccountId() {
    return accountId;
  }
}
