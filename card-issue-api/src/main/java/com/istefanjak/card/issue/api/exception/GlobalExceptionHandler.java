package com.istefanjak.card.issue.api.exception;

import com.istefanjak.card.issue.api.model.response.ExceptionResponse;
import com.istefanjak.card.issue.core.exception.CoreException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /** Handles every exception not caught in the rest of the class methods. */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> genericExceptionHandler(Exception e) {
    log.error("Error", e);
    var msg = "Internal server error";
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(msg));
  }

  /** Handles exceptions from core module. */
  @ExceptionHandler(CoreException.class)
  public ResponseEntity<ExceptionResponse> coreExceptionHandler(CoreException e) {
    log.error("Error", e);
    var status =
        switch (e.getType()) {
          case ENTITY_CONFLICT -> HttpStatus.CONFLICT;
          case ENTITY_NOT_FOUND -> HttpStatus.NOT_FOUND;
        };
    return ResponseEntity.status(status).body(new ExceptionResponse(e.getType().getMessage()));
  }

  /** Handles validation exceptions. */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ExceptionResponse> constraintViolationExceptionHandler(
      ConstraintViolationException e) {
    log.error("Error", e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new ExceptionResponse(
                e.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .toList()));
  }
}
