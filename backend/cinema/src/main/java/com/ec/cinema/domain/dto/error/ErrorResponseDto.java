package com.ec.cinema.domain.dto.error;

import org.springframework.validation.FieldError;

public record ErrorResponseDto(String field, String error) {
    public ErrorResponseDto(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }

    public ErrorResponseDto(String errorMessage) {
        this(null, errorMessage);
    }
}
