package com.jdbc.wchallenge_api.exception;

import com.jdbc.wchallenge_api.model.ErrorResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestControllerAdvice
class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<>();

    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
    }
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);

    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<>();
    errors.add("Request body: null");

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Required request body is missing!", errors);

    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<>();
    errors.add(Objects.requireNonNull(ex.getValue()).toString());

    String msg = String.format("Invalid type, should be %s", ex.getRequiredType());
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, msg, errors);

    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({IllegalArgumentException.class,})
  public ResponseEntity<Object> handleEmptyRequest(IllegalArgumentException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<>();
    errors.add("Request body can't be null or empty");

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);

    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<Object> handleNumberFormat(NumberFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return handleTypeMismatch(new TypeMismatchException(ex.getMessage(), Integer.class), headers, status, request);
  }

  @ExceptionHandler(DuplicateKeyException.class)
  public ResponseEntity<Object> handleDuplicate(org.springframework.dao.DuplicateKeyException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, "Data cannot be saved", errors);

    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllExceptions(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error ocurred", errors);

    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
