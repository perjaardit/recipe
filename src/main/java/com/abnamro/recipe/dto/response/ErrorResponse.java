package com.abnamro.recipe.dto.response;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private final Integer httpStatus;
    private final String exception;
    private final String message;
}
