package com.trustpay.account.application.dto;

public record CreateAccountDto(
  String email,
  String phoneNumber,
  String password,
  String provider
) {}
