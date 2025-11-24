package com.trustpay.account.infrastructure.config;

import com.trustpay.account.domaine.factory.*;
import com.trustpay.account.domaine.service.PasswordHasher;
import com.trustpay.account.domaine.value.AuthProvider;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfig {

  @Bean
  public LocalAccountCreationStrategy localStrategy(PasswordHasher hasher) {
    return new LocalAccountCreationStrategy(hasher);
  }

  @Bean
  public GoogleAccountCreationStrategy googleStrategy() {
    return new GoogleAccountCreationStrategy();
  }

  @Bean
  public Map<AuthProvider, AccountCreationStrategy> strategies(
    LocalAccountCreationStrategy local,
    GoogleAccountCreationStrategy google
  ) {
    return Map.of(AuthProvider.LOCAL, local, AuthProvider.GOOGLE, google);
  }
}
