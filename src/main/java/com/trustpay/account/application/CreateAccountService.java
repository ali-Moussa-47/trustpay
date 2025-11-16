package com.trustpay.account.application;

import com.trustpay.account.application.dto.CreateAccountDto;
import com.trustpay.account.domaine.AccountRepository;
import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.exception.DuplicateEmailException;
import com.trustpay.account.domaine.exception.DuplicatePhoneNumberException;
import com.trustpay.account.domaine.exception.MissingPasswordException;
import com.trustpay.account.domaine.exception.UnsupportedAuthProviderException;
import com.trustpay.account.domaine.factory.AccountCreationStrategy;
import com.trustpay.account.domaine.value.*;
import java.util.Map;

public class CreateAccountService {

  private final AccountRepository repository;
  private final Map<AuthProvider, AccountCreationStrategy> strategies;

  public CreateAccountService(
    AccountRepository repository,
    Map<AuthProvider, AccountCreationStrategy> strategies
  ) {
    this.repository = repository;
    this.strategies = strategies;
  }

  public AccountId handle(CreateAccountDto dto) {
    AuthProvider provider = AuthProvider.valueOf(dto.provider());
    AccountCreationStrategy strategy = strategies.get(provider);

    if (strategy == null) {
      throw new UnsupportedAuthProviderException("Unsupported provider: " + provider);
    }

    Email email = new Email(dto.email());
    PhoneNumber phone = new PhoneNumber(dto.phoneNumber());
    PlainPassword password = dto.password() != null
      ? new PlainPassword(dto.password())
      : null;

    validateBeforeCreation(provider, email, phone, password);

    Account account = strategy.create(AccountId.newId(), email, phone, password);

    repository.save(account);

    return account.getAccountId();
  }

  private void validateBeforeCreation(
    AuthProvider provider,
    Email email,
    PhoneNumber phone,
    PlainPassword password
  ) {
    if (provider == AuthProvider.LOCAL && password == null) {
      throw new MissingPasswordException("Password required for LOCAL provider");
    }

    if (repository.existsByEmail(email)) {
      throw new DuplicateEmailException("Email already registered");
    }

    if (repository.existsByPhone(phone)) {
      throw new DuplicatePhoneNumberException("Phone number already registered");
    }
  }
}
