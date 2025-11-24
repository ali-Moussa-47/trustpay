package com.trustpay.account.resource;

public record CreateAccountRequest(
  String email,
  String phoneNumber,
  String password,
  String provider
) {}
