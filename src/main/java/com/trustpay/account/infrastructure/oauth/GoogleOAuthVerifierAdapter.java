package com.trustpay.account.infrastructure.oauth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.trustpay.account.application.port.out.GoogleOAuthVerifierPort;
import com.trustpay.account.application.port.out.GoogleUserInfo;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuthVerifierAdapter implements GoogleOAuthVerifierPort {

  private final GoogleIdTokenVerifier verifier;

  public GoogleOAuthVerifierAdapter(
    @Value("${security.google.client-id}") String clientId
  ) {
    this.verifier =
      new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
        .setAudience(Collections.singletonList(clientId))
        .build();
  }

  @Override
  public GoogleUserInfo verifyIdToken(String idTokenString) {
    try {
      GoogleIdToken idToken = verifier.verify(idTokenString);

      if (idToken == null) {
        throw new IllegalArgumentException("Invalid Google ID Token");
      }

      GoogleIdToken.Payload payload = idToken.getPayload();

      return new GoogleUserInfo(
        payload.getEmail(),
        payload.getSubject(),
        (String) payload.get("given_name"),
        (String) payload.get("family_name"),
        (String) payload.get("picture")
      );
    } catch (Exception ex) {
      throw new IllegalArgumentException("Failed to verify Google ID Token", ex);
    }
  }
}
