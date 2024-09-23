package com.istefanjak.card.issue.api.model.response;

import com.istefanjak.card.issue.core.model.CardStatus;
import java.util.List;

public record AccountResponse(
    String oib, String firstName, String lastName, List<AccountResponseCard> cards) {
  public record AccountResponseCard(Long id, CardStatus status) {}
}
