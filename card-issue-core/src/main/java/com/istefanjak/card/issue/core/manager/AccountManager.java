package com.istefanjak.card.issue.core.manager;

import com.istefanjak.card.issue.core.exception.CoreException;
import com.istefanjak.card.issue.core.exception.ExceptionType;
import com.istefanjak.card.issue.core.model.Account;
import com.istefanjak.card.issue.core.repository.AccountRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Manager class for handling account operations. */
@Service
public class AccountManager {

  private final AccountRepository repository;

  public AccountManager(AccountRepository repository) {
    this.repository = repository;
  }

  /**
   * Retrieves an account by its OIB.
   *
   * @param oib the OIB of the account to retrieve
   * @return the {@link Account} containing account details
   */
  public Account getAccountByOib(String oib) {
    return repository
        .findByOib(oib)
        .orElseThrow(() -> new CoreException(ExceptionType.ENTITY_NOT_FOUND));
  }

  /**
   * Inserts a new account based on the provided account details.
   *
   * @param account the account to create
   * @return the ID of the newly created account
   */
  public Long insertAccount(Account account) {
    try {
      return repository.save(account).getId();
    } catch (DataIntegrityViolationException e) {
      throw new CoreException(ExceptionType.ENTITY_CONFLICT);
    }
  }

  /**
   * Deletes an account by its OIB.
   *
   * @param oib the OIB of the account to delete
   */
  @Transactional
  public void deleteAccount(String oib) {
    var rowsDeleted = repository.deleteByOib(oib);
    if (rowsDeleted == 0) {
      throw new CoreException(ExceptionType.ENTITY_NOT_FOUND);
    }
  }
}
