package com.istefanjak.card.issue.core.exception;

/** Objects of this class are thrown inside the Core module. */
public class CoreException extends RuntimeException {

  private final ExceptionType type;

  public CoreException(ExceptionType type) {
    this.type = type;
  }

  public ExceptionType getType() {
    return type;
  }
}
