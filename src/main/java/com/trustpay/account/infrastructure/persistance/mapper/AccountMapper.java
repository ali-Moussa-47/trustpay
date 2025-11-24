package com.trustpay.account.infrastructure.persistance.mapper;

import com.trustpay.account.domaine.entity.Account;
import com.trustpay.account.domaine.value.*;
import com.trustpay.account.infrastructure.persistance.AccountJpaEntity;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = { Collectors.class })
public interface AccountMapper {
  @Mapping(
    target = "id",
    expression = "java(UUID.fromString(account.getAccountId().value()))"
  )
  @Mapping(target = "email", expression = "java(account.getEmail().email())")
  @Mapping(
    target = "phoneNumber",
    expression = "java(account.getPhoneNumber() != null ? account.getPhoneNumber().value() : null)"
  )
  @Mapping(target = "authProvider", expression = "java(account.getAuthProvider().name())")
  @Mapping(
    target = "supportedMethods",
    expression = "java(account.getSupportedMethods().stream().map(Enum::name).collect(Collectors.toSet()))"
  )
  @Mapping(
    target = "passwordHash",
    expression = "java(account.getPasswordHash() != null ? account.getPasswordHash().value() : null)"
  )
  AccountJpaEntity toJpa(Account account);

  @Mapping(
    target = "accountId",
    expression = "java(new AccountId(entity.getId().toString()))"
  )
  @Mapping(target = "email", expression = "java(new Email(entity.getEmail()))")
  @Mapping(
    target = "phoneNumber",
    expression = "java(entity.getPhoneNumber() != null ? new PhoneNumber(entity.getPhoneNumber()) : null)"
  )
  @Mapping(
    target = "authProvider",
    expression = "java(AuthProvider.valueOf(entity.getAuthProvider()))"
  )
  @Mapping(
    target = "supportedMethods",
    expression = "java(entity.getSupportedMethods().stream().map(AuthMethod::valueOf).collect(Collectors.toUnmodifiableSet()))"
  )
  @Mapping(
    target = "passwordHash",
    expression = "java(entity.getPasswordHash() != null ? new PasswordHash(entity.getPasswordHash()) : null)"
  )
  Account toDomain(AccountJpaEntity entity);
}
