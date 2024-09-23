package com.istefanjak.card.issue.api.controller;

import com.istefanjak.card.issue.api.model.response.ConstantsResponse;
import com.istefanjak.card.issue.core.model.CardStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/const")
public class ConstantsController {

  @Operation(
      summary = "Returns all application constants",
      description = "Returns all application constants and settings.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Constants returned successfully"),
      })
  @GetMapping
  public ConstantsResponse getConstants() {
    return new ConstantsResponse(Arrays.asList(CardStatus.values()));
  }
}
