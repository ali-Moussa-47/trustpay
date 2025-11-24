package com.trustpay.account.infrastructure.web;

import com.trustpay.account.application.command.AuthenticateWithGoogleCommand;
import com.trustpay.account.application.port.in.AuthenticateWithGoogleUseCase;
import com.trustpay.account.application.port.out.AuthTokens;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/google")
public class GoogleAuthController {

  private final AuthenticateWithGoogleUseCase authenticateWithGoogle;

  public GoogleAuthController(AuthenticateWithGoogleUseCase authenticateWithGoogle) {
    this.authenticateWithGoogle = authenticateWithGoogle;
  }

  @PostMapping
  public ResponseEntity<AuthTokensResponse> authenticate(
    @RequestBody GoogleAuthRequest request
  ) {
    AuthTokens tokens = authenticateWithGoogle.authenticate(
      new AuthenticateWithGoogleCommand(request.idToken())
    );

    return ResponseEntity.ok(
      new AuthTokensResponse(tokens.accessToken(), tokens.refreshToken())
    );
  }
}
