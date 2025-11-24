package com.trustpay.account.infrastructure.adapter;

import com.trustpay.account.application.port.out.AccountCreatorPort;
import com.trustpay.account.domaine.AccountRepository;
import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.factory.AccountCreationStrategy;
import com.trustpay.account.domaine.value.*;
import org.springframework.stereotype.Component;

@Component
public class OAuthAccountCreatorAdapter implements AccountCreatorPort {

  private final AccountRepository repository;
  private final AccountCreationStrategy googleStrategy;

  public OAuthAccountCreatorAdapter(
    AccountRepository repository,
    AccountCreationStrategy googleStrategy
  ) {
    this.repository = repository;
    this.googleStrategy = googleStrategy;
  }

  @Override
  public Account createOAuthAccount(Email email, String providerUserId) {
    Account account = googleStrategy.create(AccountId.newId(), email, null, null);

    repository.save(account);
    return account;
  }
}
