package com.istefanjak.card.issue.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istefanjak.card.issue.core.manager.AccountManager;
import com.istefanjak.card.issue.core.model.Account;
import com.istefanjak.card.issue.core.model.Card;
import com.istefanjak.card.issue.core.model.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class IntgTest {
  protected static final String OIB_1 = "47042194806";
  protected static final String OIB_2 = "42197321610";

  protected static final String OIB_INVALID_1 = "42197321611";

  protected static final Card CARD_1 = new Card(CardStatus.APPROVED);
  protected static final Card CARD_2 = new Card(CardStatus.PENDING);
  protected static final Card CARD_3 = new Card(CardStatus.REJECTED);

  protected static final Account ACCOUNT_1 = new Account("54594283195", "Luka", "Horvat");
  protected static final Account ACCOUNT_2 = new Account("79843453464", "Marica", "Testić");

  protected static final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired protected AccountManager manager;

  protected void initializeDatabase() {
    ACCOUNT_1.addCards(CARD_1, CARD_2);
    manager.insertAccount(ACCOUNT_1);

    ACCOUNT_2.addCards(CARD_3);
    manager.insertAccount(ACCOUNT_2);
  }
}
