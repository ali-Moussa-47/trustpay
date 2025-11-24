package com.trustpay.account.infrastructure.persistance;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor
public class AccountJpaEntity {

  @Id
  private UUID id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(unique = true)
  private String phoneNumber;

  @Column(nullable = false)
  private String authProvider;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
    name = "account_auth_methods",
    joinColumns = @JoinColumn(name = "account_id")
  )
  @Column(name = "auth_method")
  private Set<String> supportedMethods;

  private String passwordHash;

  public AccountJpaEntity(
    UUID id,
    String email,
    String phoneNumber,
    String authProvider,
    Set<String> supportedMethods,
    String passwordHash
  ) {
    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.authProvider = authProvider;
    this.supportedMethods = supportedMethods;
    this.passwordHash = passwordHash;
  }
}
