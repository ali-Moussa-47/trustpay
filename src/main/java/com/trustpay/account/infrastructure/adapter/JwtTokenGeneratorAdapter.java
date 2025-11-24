package com.trustpay.account.infrastructure.adapter;

import com.trustpay.account.application.port.out.AuthTokens;
import com.trustpay.account.application.port.out.TokenGeneratorPort;
import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.infrastructure.config.TokenProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGeneratorAdapter implements TokenGeneratorPort {

  private final TokenProperties props;

  public JwtTokenGeneratorAdapter(TokenProperties props) {
    this.props = props;
  }

  @Override
  public AuthTokens generateFor(Account account) {
    Instant now = Instant.now();

    String accessToken = Jwts
      .builder()
      .setSubject(account.getAccountId().value().toString())
      .setIssuer(props.issuer())
      .setIssuedAt(Date.from(now))
      .setExpiration(Date.from(now.plusSeconds(props.accessTokenTtlSeconds())))
      .claim("email", account.getEmail().email())
      .claim("provider", account.getAuthProvider().name())
      .signWith(Keys.hmacShaKeyFor(props.secret().getBytes()), SignatureAlgorithm.HS256)
      .compact();

    String refreshToken = Jwts
      .builder()
      .setSubject(account.getAccountId().value().toString())
      .setIssuer(props.issuer())
      .setIssuedAt(Date.from(now))
      .setExpiration(Date.from(now.plusSeconds(props.refreshTokenTtlSeconds())))
      .claim("type", "refresh")
      .signWith(Keys.hmacShaKeyFor(props.secret().getBytes()), SignatureAlgorithm.HS256)
      .compact();

    return new AuthTokens(accessToken, refreshToken);
  }
}
