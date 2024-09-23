package com.istefanjak.card.issue.api.controller;

import com.istefanjak.card.issue.api.model.request.CardChangeRequest;
import com.istefanjak.card.issue.api.model.request.CardRequest;
import com.istefanjak.card.issue.api.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
  private final CardService cardService;

  public CardController(CardService cardService) {
    this.cardService = cardService;
  }

  @Operation(
      summary = "Create a new card",
      description = "Create a new card for customer's account given customer's OIB.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Card created successfully, returns the ID of the created card",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Long.class))),
        @ApiResponse(responseCode = "404", description = "Account with given OIB doesn't exist"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long postCard(@RequestBody CardRequest cardRequest) {
    return cardService.insertCard(cardRequest);
  }

  @Operation(
      summary = "Delete card by ID",
      description = "Deletes a card based on the provided card ID.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Card deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Card by given ID not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCard(@PathVariable Long id) {
    cardService.deleteCard(id);
  }

  @Operation(
      summary = "Update an existing card",
      description = "Updates the card details for the specified ID using the provided changes.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Card updated successfully"),
        @ApiResponse(responseCode = "404", description = "Card by given ID not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void putCard(@PathVariable Long id, @RequestBody CardChangeRequest cardChangeRequest) {
    cardService.changeCard(id, cardChangeRequest);
  }
}
