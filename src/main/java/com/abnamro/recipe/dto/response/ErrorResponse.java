package com.abnamro.recipe.dto.response;

public record ErrorResponse(Integer httpStatus, String exception, String message) {
}
