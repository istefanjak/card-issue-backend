package com.istefanjak.card.issue.api.model.response;

import java.util.Arrays;
import java.util.List;

public record ExceptionResponse(List<String> messages) {
  public ExceptionResponse(String message) {
    this(List.of(message));
  }

  public ExceptionResponse(String... message) {
    this(Arrays.asList(message));
  }
}
