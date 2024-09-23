package com.istefanjak.card.issue.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.istefanjak.card.issue.api.IntgTest;
import com.istefanjak.card.issue.api.model.request.AccountRequest;
import com.istefanjak.card.issue.integration.client.CardClient;
import com.istefanjak.card.issue.integration.model.request.CardRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest extends IntgTest {
  @Autowired MockMvc mockMvc;

  @MockBean
  CardClient cardClient;

  @Test
  @DirtiesContext
  void getAccount_givenOib_returnsValidAccount() throws Exception {
    initializeDatabase();

    var account = ACCOUNT_1;

    mockMvc
        .perform(get("/account/{oib}", account.getOib()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.oib").value(account.getOib()))
        .andExpect(jsonPath("$.firstName").value(account.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(account.getLastName()))
        .andExpect(jsonPath("$.cards").isArray())
        .andExpect(jsonPath("$.cards").isNotEmpty());

    // Test for external card service API calls
    verify(cardClient, times(account.getCards().size())).request(any(CardRequest.class));
  }

  @Test
  @DirtiesContext
  void getAccount_givenNonExistentValidOib_exceptionResponse() throws Exception {
    initializeDatabase();

    mockMvc
        .perform(get("/account/{oib}", OIB_1))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.messages").isArray());
  }

  @Test
  @DirtiesContext
  void getAccount_givenInvalidOib_exceptionResponse() throws Exception {
    initializeDatabase();

    mockMvc
        .perform(get("/account/{oib}", OIB_INVALID_1))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.messages").isArray());
  }

  @Test
  @DirtiesContext
  void postAccount_givenValidRequest_responseCreated() throws Exception {
    var account = ACCOUNT_1;

    var request =
        new AccountRequest(account.getOib(), account.getFirstName(), account.getLastName());

    mockMvc
        .perform(
            post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").isNumber());
  }

  @Test
  @DirtiesContext
  void postAccount_givenValidRequestWithExistingOib_responseConflict() throws Exception {
    initializeDatabase();

    var account = ACCOUNT_1;

    var request =
        new AccountRequest(account.getOib(), account.getFirstName(), account.getLastName());

    mockMvc
        .perform(
            post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.messages").isArray());
  }

  @Test
  @DirtiesContext
  void deleteAccount_givenOib_responseNoContent() throws Exception {
    initializeDatabase();

    mockMvc
        .perform(delete("/account/{oib}", ACCOUNT_1.getOib()))
        .andExpect(status().isNoContent());
  }

  @Test
  @DirtiesContext
  void deleteAccount_givenNonExistingOib_responseNotFound() throws Exception {
    mockMvc
        .perform(delete("/account/{oib}", OIB_1))
        .andExpect(status().isNotFound());
  }
}
