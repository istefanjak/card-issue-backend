package com.istefanjak.card.issue.api.service;

import com.istefanjak.card.issue.core.manager.AccountManager;
import com.istefanjak.card.issue.core.model.Account;
import com.istefanjak.card.issue.core.model.Card;
import com.istefanjak.card.issue.core.model.CardStatus;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * A service that populates the database with test data upon application start. Meant to be used
 * with "dev" profile
 */
@Service
@Profile("dev")
public class DbPopulatorService {
  private static final Logger log = LoggerFactory.getLogger(DbPopulatorService.class);

  public static final Card CARD_1 = new Card(CardStatus.APPROVED);
  public static final Card CARD_2 = new Card(CardStatus.PENDING);
  public static final Card CARD_3 = new Card(CardStatus.REJECTED);

  private static final Account ACCOUNT_1 = new Account("54594283195", "Luka", "Horvat");
  private static final Account ACCOUNT_2 = new Account("79843453464", "Marica", "Testić");

  private final AccountManager accountManager;

  public DbPopulatorService(AccountManager accountManager) {
    this.accountManager = accountManager;
  }

  @EventListener
  @Transactional
  public void appReady(ApplicationReadyEvent event) {
    ACCOUNT_1.addCards(CARD_1, CARD_2);
    accountManager.insertAccount(ACCOUNT_1);
    log.info("Inserted {}", ACCOUNT_1);

    ACCOUNT_2.addCards(CARD_3);
    accountManager.insertAccount(ACCOUNT_2);
    log.info("Inserted {}", ACCOUNT_2);
  }
}
