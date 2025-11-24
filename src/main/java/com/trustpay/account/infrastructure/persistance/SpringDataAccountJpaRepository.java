package com.trustpay.account.infrastructure.persistance;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountJpaRepository
  extends JpaRepository<AccountJpaEntity, UUID> {
  Optional<AccountJpaEntity> findByEmail(String email);
  boolean existsByEmail(String email);
  boolean existsByPhoneNumber(String phoneNumber);
}
