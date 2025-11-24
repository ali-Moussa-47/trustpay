package com.trustpay.account.application.port.out;

import com.trustpay.account.domaine.entity.Account;

public interface TokenGeneratorPort {
  AuthTokens generateFor(Account account);
}
