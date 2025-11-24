package com.trustpay.account.infrastructure.config;

import com.trustpay.account.domaine.service.PasswordHasher;
import com.trustpay.account.infrastructure.security.BCryptPasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {

  @Bean
  public PasswordHasher passwordHasher() {
    return new BCryptPasswordHasher(10);
  }
}
