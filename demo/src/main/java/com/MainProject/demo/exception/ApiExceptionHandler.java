package com.MainProject.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
    ApiException apiException = new ApiException(
            exception.getMessage(),
            exception.getCause(),
            HttpStatus.NOT_FOUND
    );
    return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {EntityAlreadyExistsException.class})
  public ResponseEntity<Object> handleEntityAlreadyExistsException(EntityAlreadyExistsException exception) {
    ApiException apiException = new ApiException(
            exception.getMessage(),
            exception.getCause(),
            HttpStatus.CONFLICT
    );
    return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
  }
}