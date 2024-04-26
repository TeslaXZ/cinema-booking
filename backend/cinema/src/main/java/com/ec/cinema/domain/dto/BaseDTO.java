package com.ec.cinema.domain.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class BaseDTO {
    private Long id;
    @NotNull
    private Boolean status;
}
