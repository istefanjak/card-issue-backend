package com.istefanjak.card.issue.api.controller;

import com.istefanjak.card.issue.api.model.request.AccountRequest;
import com.istefanjak.card.issue.api.model.response.AccountResponse;
import com.istefanjak.card.issue.api.service.AccountService;
import com.istefanjak.card.issue.core.validation.oib.Oib;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Validated
public class AccountController {
  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @Operation(
      summary = "Get account by OIB",
      description = "Retrieves an account based on the provided OIB.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of account"),
        @ApiResponse(responseCode = "400", description = "Invalid OIB"),
        @ApiResponse(responseCode = "404", description = "Account not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @GetMapping("/{oib}")
  public AccountResponse getAccount(@PathVariable @Oib String oib) {
    return accountService.getAccountByOib(oib);
  }

  @Operation(
      summary = "Create a new account",
      description = "Creates a new account based on the provided account request details.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Account created successfully, returns the ID of the created account",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Long.class))),
        @ApiResponse(responseCode = "409", description = "Account by given OIB already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long postAccount(@RequestBody AccountRequest accountRequest) {
    return accountService.insertAccount(accountRequest);
  }

  @Operation(
      summary = "Delete account by OIB",
      description = "Deletes an account based on the provided OIB.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Account not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @DeleteMapping("/{oib}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAccount(@PathVariable @Oib String oib) {
    accountService.deleteAccount(oib);
  }
}
