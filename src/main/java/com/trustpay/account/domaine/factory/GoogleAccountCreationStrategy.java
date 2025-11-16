package com.trustpay.account.domaine.factory;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.*;
import java.util.Set;

public class GoogleAccountCreationStrategy implements AccountCreationStrategy {

  @Override
  public Account create(
    AccountId id,
    Email email,
    PhoneNumber phone,
    PlainPassword ignored
  ) {
    return new Account(
      id,
      email,
      phone,
      AuthProvider.GOOGLE,
      Set.of(AuthMethod.OAUTH),
      null
    );
  }
}
