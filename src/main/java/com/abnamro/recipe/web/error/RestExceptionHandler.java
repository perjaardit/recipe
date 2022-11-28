package com.abnamro.recipe.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleStatusException(final ResponseStatusException exception) {
        return new ResponseEntity<>(
                ErrorResponse.builder(exception, exception.getStatusCode(), exception.getMessage())
                        .build(),
                exception.getStatusCode());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<org.springframework.web.ErrorResponse> handleThrowable(final Throwable exception) {
        return new ResponseEntity<>(
                ErrorResponse.builder(exception, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
