package com.ec.cinema.domain.dto.movie;

import com.ec.cinema.domain.dto.BaseDTO;
import com.ec.cinema.domain.enums.MovieGenreEnum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MovieDTO extends BaseDTO{
    @NotEmpty
    @Size(max = 30, min = 1)
    private String name;
    @NotNull
    private MovieGenreEnum genre;
    @NotNull
    @Min(value = 1)
    private Short allowedAge;
    @NotNull
    @Min(value = 1)
    private Short lengthMinutes;


}
