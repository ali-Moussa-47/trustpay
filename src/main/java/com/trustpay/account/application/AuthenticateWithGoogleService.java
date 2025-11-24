package com.trustpay.account.application;

import com.trustpay.account.application.command.AuthenticateWithGoogleCommand;
import com.trustpay.account.application.port.in.AuthenticateWithGoogleUseCase;
import com.trustpay.account.application.port.out.*;
import com.trustpay.account.domaine.AccountRepository;
import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticateWithGoogleService implements AuthenticateWithGoogleUseCase {

  private final GoogleOAuthVerifierPort googleVerifier;
  private final AccountRepository accountRepository;
  private final AccountCreatorPort accountCreator;
  private final TokenGeneratorPort tokenGenerator;

  public AuthenticateWithGoogleService(
    GoogleOAuthVerifierPort googleVerifier,
    AccountRepository accountRepository,
    AccountCreatorPort accountCreator,
    TokenGeneratorPort tokenGenerator
  ) {
    this.googleVerifier = googleVerifier;
    this.accountRepository = accountRepository;
    this.accountCreator = accountCreator;
    this.tokenGenerator = tokenGenerator;
  }

  @Override
  @Transactional
  public AuthTokens authenticate(AuthenticateWithGoogleCommand command) {
    GoogleUserInfo userInfo = googleVerifier.verifyIdToken(command.idToken());

    Email email = new Email(userInfo.email());

    Account account = accountRepository
      .findByEmail(email)
      .orElseGet(() -> accountCreator.createOAuthAccount(email, userInfo.googleSub()));

    return tokenGenerator.generateFor(account);
  }
}
