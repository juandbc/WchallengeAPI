package com.jdbc.wchallenge_api.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public class ErrorResponse {

  private LocalDateTime timestamp;
  private int status;
  private String reason;
  private String message;
  private List<String> errors;

  public ErrorResponse() {
  }

  public ErrorResponse(HttpStatus status, String message, List<String> errors) {
    this.timestamp = LocalDateTime.now();
    this.reason = status.getReasonPhrase();
    this.status = status.value();
    this.message = message;
    this.errors = errors;
  }

  public ErrorResponse(LocalDateTime timestamp, int status, String reason, String message, List<String> errors) {
    this.timestamp = timestamp;
    this.status = status;
    this.reason = reason;
    this.message = message;
    this.errors = errors;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  @Override
  public String toString() {
    return "ErrorResponse{" +
            "timestamp=" + timestamp +
            ", status=" + status +
            ", reason='" + reason + '\'' +
            ", message='" + message + '\'' +
            ", errors=" + errors +
            '}';
  }
}
