package com.trustpay.account.resource;

import com.trustpay.account.domaine.exception.ErrorType;

public record ErrorResponse(ErrorType status, String message) {}
