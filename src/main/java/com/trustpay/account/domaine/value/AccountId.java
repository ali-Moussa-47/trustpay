package com.trustpay.account.domaine.value;

import com.trustpay.account.domaine.exception.IdInvalidException;
import org.apache.commons.lang3.StringUtils;

public record AccountId(String value) {

    public AccountId {
        validate(value);
    }

    private void validate(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IdInvalidException("ID cannot be null or empty.");
        }
    }

    public static AccountId from(String raw) {
        return new AccountId(raw);
    }
}
