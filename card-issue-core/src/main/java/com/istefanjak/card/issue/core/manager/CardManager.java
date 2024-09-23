package com.istefanjak.card.issue.core.manager;

import com.istefanjak.card.issue.core.exception.CoreException;
import com.istefanjak.card.issue.core.exception.ExceptionType;
import com.istefanjak.card.issue.core.model.Account;
import com.istefanjak.card.issue.core.model.Card;
import com.istefanjak.card.issue.core.model.CardStatus;
import com.istefanjak.card.issue.core.repository.CardRepository;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Manager class for handling card operations. */
@Service
public class CardManager {

  private final CardRepository repository;

  public CardManager(CardRepository repository) {
    this.repository = repository;
  }

  /**
   * Inserts a new card to the given account.
   *
   * @param account account the card is going to be tied to
   * @param card card to be inserted
   * @return the ID of the newly created card
   */
  public Long insertCard(Account account, Card card) {
    account.addCards(card);
    try {
      return repository.save(card).getId();
    } catch (DataIntegrityViolationException e) {
      throw new CoreException(ExceptionType.ENTITY_CONFLICT);
    }
  }

  /**
   * Deletes a card by its ID.
   *
   * @param id the ID of the card to delete
   */
  @Transactional
  public void deleteCard(Long id) {
    var rowsDeleted = repository.deleteByIdAndReturnCount(id);
    if (rowsDeleted == 0) {
      throw new CoreException(ExceptionType.ENTITY_NOT_FOUND);
    }
  }

  /**
   * Changes the status of an existing card.
   *
   * @param id the ID of the card to update
   * @param newStatus new card status
   */
  public void changeCardStatus(Long id, CardStatus newStatus) {
    var card =
        Optional.ofNullable(id)
            .flatMap(repository::findById)
            .orElseThrow(() -> new CoreException(ExceptionType.ENTITY_NOT_FOUND));

    card.setStatus(newStatus);
    repository.save(card);
  }
}
