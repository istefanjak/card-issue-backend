package com.istefanjak.card.issue.api.model.mapper;

import com.istefanjak.card.issue.core.model.Account;
import com.istefanjak.card.issue.integration.model.request.CardRequest;
import java.util.List;

/** Maps objects to DTO's used by external card service API */
public class ClientRequestMapper {
  private ClientRequestMapper() {}

  /**
   * Maps {@link Account} to a list of {@link CardRequest}s. Every card from the provided account
   * makes one request in the list.
   */
  public static List<CardRequest> toCardRequests(Account account) {
    return account.getCards().stream()
        .map(
            card ->
                new CardRequest(
                    account.getFirstName(),
                    account.getLastName(),
                    card.getStatus().name(),
                    account.getOib()))
        .toList();
  }
}
