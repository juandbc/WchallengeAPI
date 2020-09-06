package com.jdbc.wchallenge_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestControllerAdvice
class ControllerAdvisor {

  @ExceptionHandler(value = NumberFormatException.class)
  public void handleBadId(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid id");
  }
}
