package com.istefanjak.card.issue.api.model.mapper;

import com.istefanjak.card.issue.api.model.response.AccountResponse;
import com.istefanjak.card.issue.api.model.response.AccountResponse.AccountResponseCard;
import com.istefanjak.card.issue.core.model.Account;

/** Maps domain objects to response objects. */
public class ResponseMapper {
  private ResponseMapper() {}

  public static AccountResponse toAccountResponse(Account account) {
    return new AccountResponse(
        account.getOib(),
        account.getFirstName(),
        account.getLastName(),
        account.getCards().stream()
            .map(c -> new AccountResponseCard(c.getId(), c.getStatus()))
            .toList());
  }
}
