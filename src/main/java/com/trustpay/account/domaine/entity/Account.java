package com.trustpay.account.domaine.entity;

import com.trustpay.account.domaine.value.AccountId;
import com.trustpay.account.domaine.value.Email;
import com.trustpay.account.domaine.value.PasswordHash;
import com.trustpay.account.domaine.value.PhoneNumber;

public class Account {
    private final AccountId accountId;
    private final Email email;
    private final PasswordHash passwordHash;
    private final PhoneNumber phoneNumber;


    public Account(AccountId accountId, Email email, PasswordHash passwordHash, PhoneNumber phoneNumber) {
        this.accountId = accountId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
    }

}
