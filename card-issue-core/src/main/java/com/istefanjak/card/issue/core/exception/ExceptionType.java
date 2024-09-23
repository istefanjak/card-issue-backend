package com.istefanjak.card.issue.core.exception;

/**
 * Exception types to be used with {@link CoreException}. Each exception type contains a code and a
 * message.
 */
public enum ExceptionType {
  ENTITY_CONFLICT("001", "Entity exists"),
  ENTITY_NOT_FOUND("002", "Entity not found"),
  ;

  private final String code;
  private final String message;

  ExceptionType(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
