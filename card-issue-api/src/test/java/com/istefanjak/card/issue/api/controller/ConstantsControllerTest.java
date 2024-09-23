package com.istefanjak.card.issue.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.istefanjak.card.issue.api.model.request.CardRequest;
import com.istefanjak.card.issue.core.model.CardStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ConstantsControllerTest {
  @Autowired MockMvc mockMvc;

  @Test
  @DirtiesContext
  void getConstants_givenValidRequest_responseOk() throws Exception {
    mockMvc
        .perform(get("/const"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.cardStatus").isArray())
        .andExpect(jsonPath("$.cardStatus").isNotEmpty());
  }
}
