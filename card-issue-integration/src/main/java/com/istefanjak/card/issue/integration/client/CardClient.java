package com.istefanjak.card.issue.integration.client;

import com.istefanjak.card.issue.integration.exception.ExceptionType;
import com.istefanjak.card.issue.integration.exception.IntegrationException;
import com.istefanjak.card.issue.integration.model.request.CardRequest;
import com.istefanjak.card.issue.integration.model.response.ErrorResponse;
import com.istefanjak.card.issue.integration.path.CardClientPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/** Client class containing operations for the external card service API */
public class CardClient {
  private final RestTemplate restTemplate;
  private final String baseUrl;

  public CardClient(RestTemplate restTemplate, String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  /** Send a request to the API that records every card search. */
  public void request(CardRequest cardRequest) {
    var method = HttpMethod.POST;
    var url = baseUrl + CardClientPath.CARD_REQUEST;
    var headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    var requestEntity = new HttpEntity<>(cardRequest, headers);
    try {
      var response = restTemplate.exchange(url, method, requestEntity, String.class);

      if (response.getStatusCode() != HttpStatus.CREATED) {
        throw new IntegrationException(
            ExceptionType.UNEXPECTED_RESPONSE,
            method,
            url,
            cardRequest,
            HttpStatus.resolve(response.getStatusCode().value()),
            response.getBody());
      }

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      throw new IntegrationException(
          ExceptionType.EXPECTED_ERROR,
          method,
          url,
          cardRequest,
          HttpStatus.resolve(e.getStatusCode().value()),
          e.getResponseBodyAs(ErrorResponse.class));

    } catch (ResourceAccessException e) {
      throw new IntegrationException(
          ExceptionType.SERVICE_UNAVAILABLE, method, url, cardRequest, null, null);
    }
  }
}
