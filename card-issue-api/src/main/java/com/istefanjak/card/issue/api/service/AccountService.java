package com.istefanjak.card.issue.api.service;

import com.istefanjak.card.issue.api.model.mapper.ClientRequestMapper;
import com.istefanjak.card.issue.api.model.mapper.RequestMapper;
import com.istefanjak.card.issue.api.model.mapper.ResponseMapper;
import com.istefanjak.card.issue.api.model.request.AccountRequest;
import com.istefanjak.card.issue.api.model.response.AccountResponse;
import com.istefanjak.card.issue.core.manager.AccountManager;
import com.istefanjak.card.issue.integration.client.CardClient;
import com.istefanjak.card.issue.integration.exception.IntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class provides methods to retrieve, create, and delete accounts. It interacts with the
 * {@link AccountManager} for account management and external service {@link CardClient} for
 * processing related card requests.
 */
@Service
public class AccountService {
  private static final Logger log = LoggerFactory.getLogger(AccountService.class);

  private final AccountManager accountManager;
  private final CardClient cardClient;

  public AccountService(AccountManager accountManager, CardClient cardClient) {
    this.accountManager = accountManager;
    this.cardClient = cardClient;
  }

  /**
   * Retrieves an account by its OIB and sends the request to external service {@link CardClient}.
   *
   * @param oib the OIB of the account to retrieve
   * @return the {@link AccountResponse} containing account details
   */
  public AccountResponse getAccountByOib(String oib) {
    var account = accountManager.getAccountByOib(oib);

    ClientRequestMapper.toCardRequests(account)
        .forEach(
            cardRequest -> {
              try {
                cardClient.request(cardRequest);
              } catch (IntegrationException e) {
                log.error("Exception with REST client {}", e.toString());
                // For the sake of the parent method continuing execution, exception is ignored
              }
            });

    return ResponseMapper.toAccountResponse(account);
  }

  /**
   * Inserts a new account based on the provided account request details.
   *
   * @param accountRequest the details of the account to create
   * @return the ID of the newly created account
   */
  public Long insertAccount(AccountRequest accountRequest) {
    var account = RequestMapper.toAccount(accountRequest);
    return accountManager.insertAccount(account);
  }

  /**
   * Deletes an account by its OIB.
   *
   * @param oib the OIB of the account to delete
   */
  public void deleteAccount(String oib) {
    accountManager.deleteAccount(oib);
  }
}
