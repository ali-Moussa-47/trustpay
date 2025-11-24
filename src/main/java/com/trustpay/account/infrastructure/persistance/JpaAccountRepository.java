package com.trustpay.account.infrastructure.persistance;

import com.trustpay.account.domaine.AccountRepository;
import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.AccountId;
import com.trustpay.account.domaine.value.Email;
import com.trustpay.account.domaine.value.PhoneNumber;
import com.trustpay.account.infrastructure.persistance.mapper.AccountMapper;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAccountRepository implements AccountRepository {

  private final SpringDataAccountJpaRepository jpa;
  private final AccountMapper mapper;

  public JpaAccountRepository(SpringDataAccountJpaRepository jpa, AccountMapper mapper) {
    this.jpa = jpa;
    this.mapper = mapper;
  }

  @Override
  public void save(Account account) {
    jpa.save(mapper.toJpa(account));
  }

  @Override
  public Optional<Account> findById(AccountId accountId) {
    return jpa.findById(UUID.fromString(accountId.value())).map(mapper::toDomain);
  }

  @Override
  public Optional<Account> findByEmail(Email email) {
    return jpa.findByEmail(email.email()).map(mapper::toDomain);
  }

  @Override
  public boolean existsByEmail(Email email) {
    return jpa.existsByEmail(email.email());
  }

  @Override
  public boolean existsByPhone(PhoneNumber phoneNumber) {
    return jpa.existsByPhoneNumber(phoneNumber.value());
  }
}
