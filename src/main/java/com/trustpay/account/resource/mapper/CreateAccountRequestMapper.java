package com.trustpay.account.resource.mapper;

import com.trustpay.account.application.dto.CreateAccountDto;
import com.trustpay.account.resource.CreateAccountRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountRequestMapper {

  public CreateAccountDto toCommand(CreateAccountRequest request) {
    return new CreateAccountDto(
      request.email(),
      request.phoneNumber(),
      request.password(),
      request.provider()
    );
  }
}
