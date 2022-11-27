package com.abnamro.recipe.config;

import com.abnamro.recipe.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    @ApiResponse(responseCode = "4xx/5xx", description = "Error")
    public ResponseEntity<ErrorResponse> handleNotFound(final ResponseStatusException exception) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .httpStatus(exception.getStatusCode().value())
                        .exception(exception.getClass().getSimpleName())
                        .message(exception.getMessage())
                        .build(),
                exception.getStatusCode());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(final Throwable exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .exception(exception.getClass().getSimpleName())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
