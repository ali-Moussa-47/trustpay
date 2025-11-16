package com.trustpay.account.domaine;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.AccountId;
import com.trustpay.account.domaine.value.Email;
import com.trustpay.account.domaine.value.PhoneNumber;
import java.util.Optional;

public interface AccountRepository {
  void save(Account account);

  Optional<Account> findById(AccountId accountId);

  Optional<Account> findByEmail(Email email);

  boolean existsByEmail(Email email);

  boolean existsByPhone(PhoneNumber phoneNumber);
}
