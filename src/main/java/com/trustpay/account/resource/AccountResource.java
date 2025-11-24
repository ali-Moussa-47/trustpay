package com.trustpay.account.resource;

import com.trustpay.account.application.CreateAccountService;
import com.trustpay.account.application.dto.CreateAccountDto;
import com.trustpay.account.domaine.value.AccountId;
import com.trustpay.account.resource.mapper.CreateAccountRequestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountResource {

  private final CreateAccountService service;
  private final CreateAccountRequestMapper mapper;

  public AccountResource(
    CreateAccountService service,
    CreateAccountRequestMapper mapper
  ) {
    this.service = service;
    this.mapper = mapper;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody CreateAccountRequest request) {
    CreateAccountDto createAccountDto = mapper.toCommand(request);
    AccountId id = service.handle(createAccountDto);

    return ResponseEntity.ok(new CreateAccountResponse(id.value()));
  }
}
