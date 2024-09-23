package com.istefanjak.card.issue.integration.exception;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * Objects of this class are thrown inside the Integration module. It keeps info of the request and
 * response data so it can be easily logged if needed.
 */
public class IntegrationException extends RuntimeException {
  private final ExceptionType exceptionType;
  private final HttpMethod requestMethod;
  private final String requestUrl;
  private final Object requestBody;
  private final HttpStatus responseStatus;
  private final Object responseBody;

  public IntegrationException(
      ExceptionType exceptionType,
      HttpMethod requestMethod,
      String requestUrl,
      Object requestBody,
      HttpStatus responseStatus,
      Object responseBody) {
    this.exceptionType = exceptionType;
    this.requestMethod = requestMethod;
    this.requestUrl = requestUrl;
    this.requestBody = requestBody;
    this.responseStatus = responseStatus;
    this.responseBody = responseBody;
  }

  public ExceptionType getExceptionType() {
    return exceptionType;
  }

  public HttpMethod getRequestMethod() {
    return requestMethod;
  }

  public String getRequestUrl() {
    return requestUrl;
  }

  public HttpStatus getResponseStatus() {
    return responseStatus;
  }

  public Object getResponseBody() {
    return responseBody;
  }

  public Object getRequestBody() {
    return requestBody;
  }

  @Override
  public String toString() {
    return "IntegrationException{"
        + "exceptionType="
        + exceptionType
        + ", requestMethod="
        + requestMethod
        + ", requestUrl='"
        + requestUrl
        + '\''
        + ", requestBody="
        + requestBody
        + ", responseStatus="
        + responseStatus
        + ", responseBody="
        + responseBody
        + '}';
  }
}
