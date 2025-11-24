package com.trustpay.account.application.port.in;

import com.trustpay.account.application.command.AuthenticateWithGoogleCommand;
import com.trustpay.account.application.port.out.AuthTokens;

public interface AuthenticateWithGoogleUseCase {
  AuthTokens authenticate(AuthenticateWithGoogleCommand command);
}
