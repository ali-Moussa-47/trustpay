package com.trustpay.account.domaine;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.AccountId;

public interface AccountRepository {
  void save(Account account);
  Account findById(AccountId accountId);
}
