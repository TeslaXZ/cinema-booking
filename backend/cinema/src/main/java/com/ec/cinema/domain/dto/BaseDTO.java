package com.ec.cinema.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class BaseDTO {
    private Long id;
    private Boolean status;
}
