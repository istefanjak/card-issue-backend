package com.istefanjak.card.issue.api.model.mapper;

import com.istefanjak.card.issue.api.model.request.AccountRequest;
import com.istefanjak.card.issue.api.model.request.CardRequest;
import com.istefanjak.card.issue.core.model.Account;
import com.istefanjak.card.issue.core.model.Card;

/** Maps request objects to domain objects. */
public class RequestMapper {
  private RequestMapper() {}

  public static Account toAccount(AccountRequest accountRequest) {
    return new Account(accountRequest.oib(), accountRequest.firstName(), accountRequest.lastName());
  }

  public static Card toCard(CardRequest cardRequest) {
    return new Card(cardRequest.cardStatus());
  }
}
