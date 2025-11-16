package com.trustpay.account.domaine.factory;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.AccountId;
import com.trustpay.account.domaine.value.Email;
import com.trustpay.account.domaine.value.PhoneNumber;
import com.trustpay.account.domaine.value.PlainPassword;

public interface AccountCreationStrategy {
  Account create(AccountId id, Email email, PhoneNumber phone, PlainPassword password);
}
