package com.trustpay.account.application.port.out;

public interface GoogleOAuthVerifierPort {
  GoogleUserInfo verifyIdToken(String idToken);
}
