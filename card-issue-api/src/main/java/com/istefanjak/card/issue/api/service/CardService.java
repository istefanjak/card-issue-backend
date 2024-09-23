package com.istefanjak.card.issue.api.service;

import com.istefanjak.card.issue.api.model.mapper.RequestMapper;
import com.istefanjak.card.issue.api.model.request.CardChangeRequest;
import com.istefanjak.card.issue.api.model.request.CardRequest;
import com.istefanjak.card.issue.core.manager.AccountManager;
import com.istefanjak.card.issue.core.manager.CardManager;
import org.springframework.stereotype.Service;

/**
 * This class provides methods to insert, delete, and update card details. It interacts with the
 * {@link CardManager} for card management and {@link AccountManager} to retrieve associated account
 * information.
 */
@Service
public class CardService {
  private final CardManager cardManager;
  private final AccountManager accountManager;

  public CardService(CardManager cardManager, AccountManager accountManager) {
    this.cardManager = cardManager;
    this.accountManager = accountManager;
  }

  /**
   * Inserts a new card based on the provided card request details. This method retrieves the
   * account associated with the provided OIB and then inserts the new card into the system.
   *
   * @param cardRequest the details of the card to create
   * @return the ID of the newly created card
   */
  public Long insertCard(CardRequest cardRequest) {
    var account = accountManager.getAccountByOib(cardRequest.oib());
    var card = RequestMapper.toCard(cardRequest);
    return cardManager.insertCard(account, card);
  }

  /**
   * Deletes a card by its ID.
   *
   * @param id the ID of the card to delete
   */
  public void deleteCard(Long id) {
    cardManager.deleteCard(id);
  }

  /**
   * Changes the status of an existing card.
   *
   * @param id the ID of the card to update
   * @param cardChangeRequest the details of the card status change
   */
  public void changeCard(Long id, CardChangeRequest cardChangeRequest) {
    cardManager.changeCardStatus(id, cardChangeRequest.cardStatus());
  }
}
