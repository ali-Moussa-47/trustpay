package com.trustpay.account.domaine;

import com.trustpay.account.domaine.entity.Account;

public interface AccountRepository {
    void save(Account account);
    Account findById(AccountId accountId);
}
