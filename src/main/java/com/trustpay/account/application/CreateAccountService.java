package com.trustpay.account.application;

import com.trustpay.account.application.dto.CreateAccountDto;
import com.trustpay.account.domaine.AccountRepository;
import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.exception.*;
import com.trustpay.account.domaine.factory.AccountCreationStrategy;
import com.trustpay.account.domaine.value.*;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
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

    PhoneNumber phone = dto.phoneNumber() != null
      ? new PhoneNumber(dto.phoneNumber())
      : null;

    PlainPassword password = dto.password() != null
      ? new PlainPassword(dto.password())
      : null;

    validate(provider, email, phone, password);

    Account account = strategy.create(AccountId.newId(), email, phone, password);

    repository.save(account);

    return account.getAccountId();
  }

  private void validate(
    AuthProvider provider,
    Email email,
    PhoneNumber phone,
    PlainPassword password
  ) {
    if (provider == AuthProvider.LOCAL) {
      if (password == null) {
        throw new MissingPasswordException("Password required for LOCAL provider");
      }

      if (phone == null) {
        throw new PhoneNumberInvalidException("Phone number required for LOCAL provider");
      }
    }

    if (repository.existsByEmail(email)) {
      throw new DuplicateEmailException("Email already registered");
    }
    if (AuthProvider.GOOGLE == provider) {}

    if (phone != null && repository.existsByPhone(phone)) {
      throw new DuplicatePhoneNumberException("Phone number already registered");
    }
  }
}
