package com.istefanjak.card.issue.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.istefanjak.card.issue.api.IntgTest;
import com.istefanjak.card.issue.api.model.request.CardChangeRequest;
import com.istefanjak.card.issue.api.model.request.CardRequest;
import com.istefanjak.card.issue.core.model.CardStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest extends IntgTest {
  @Autowired MockMvc mockMvc;

  @Test
  @DirtiesContext
  void postCard_givenValidRequest_responseCreated() throws Exception {
    initializeDatabase();

    var request = new CardRequest(ACCOUNT_1.getOib(), CardStatus.PENDING);

    mockMvc
        .perform(
            post("/card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").isNumber());
  }

  @Test
  @DirtiesContext
  void postCard_nonExistentOib_responseNotFound() throws Exception {
    var request = new CardRequest(ACCOUNT_1.getOib(), CardStatus.PENDING);

    mockMvc
        .perform(
            post("/card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.messages").isArray());
  }

  @Test
  @DirtiesContext
  void deleteCard_givenId_responseNoContent() throws Exception {
    initializeDatabase();

    mockMvc
        .perform(delete("/card/{id}", ACCOUNT_1.getCards().getFirst().getId()))
        .andExpect(status().isNoContent());
  }

  @Test
  @DirtiesContext
  void deleteCard_givenNonExistingId_responseNotFound() throws Exception {
    mockMvc.perform(delete("/card/{id}", CARD_1.getId())).andExpect(status().isNotFound());
  }

  @Test
  @DirtiesContext
  void putCard_givenId_responseNoContent() throws Exception {
    initializeDatabase();

    var request = new CardChangeRequest(CardStatus.APPROVED);

    mockMvc
        .perform(
            put("/card/{id}", ACCOUNT_1.getCards().getFirst().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNoContent());
  }

  @Test
  @DirtiesContext
  void putCard_givenNonExistingId_responseNotFound() throws Exception {
    var request = new CardChangeRequest(CardStatus.APPROVED);

    mockMvc
        .perform(
            put("/card/{id}", CARD_1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNotFound());
  }
}
