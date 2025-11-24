package com.trustpay.account.application.port.out;

public record GoogleUserInfo(
  String email,
  String googleSub,
  String givenName,
  String familyName,
  String pictureUrl
) {}
