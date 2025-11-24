package com.trustpay.account.domaine.entity;

import com.trustpay.account.domaine.exception.CannotChangePasswordForOAuthAccountException;
import com.trustpay.account.domaine.value.*;
import java.util.Collections;
import java.util.Set;
import lombok.Getter;

@Getter
public class Account {

  private final AccountId accountId;
  private final Email email;
  private final PhoneNumber phoneNumber;
  private final AuthProvider authProvider;
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
    this.supportedMethods = Collections.unmodifiableSet(supportedMethods);
    this.passwordHash = passwordHash;
  }

  public boolean supports(AuthMethod method) {
    return supportedMethods.contains(method);
  }

  public void changePasswordHash(PasswordHash newHash) {
    if (!supports(AuthMethod.PASSWORD)) {
      throw new CannotChangePasswordForOAuthAccountException(
        "This account cannot change password because it does not support PASSWORD method"
      );
    }

    this.passwordHash = newHash;
  }
}
