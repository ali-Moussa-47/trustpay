package com.trustpay.account.domaine.factory;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.AccountId;
import com.trustpay.account.domaine.value.AuthMethod;
import com.trustpay.account.domaine.value.AuthProvider;
import com.trustpay.account.domaine.value.Email;
import com.trustpay.account.domaine.value.PhoneNumber;
import com.trustpay.account.domaine.value.PlainPassword;
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
      null,
      AuthProvider.GOOGLE,
      Set.of(AuthMethod.OAUTH),
      null
    );
  }
}
