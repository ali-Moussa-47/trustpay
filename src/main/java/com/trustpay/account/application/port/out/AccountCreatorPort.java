package com.trustpay.account.application.port.out;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.Email;

public interface AccountCreatorPort {
  Account createOAuthAccount(Email email, String providerUserId);
}
