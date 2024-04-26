package com.ec.cinema.domain.entity;

import com.ec.cinema.domain.enums.MovieGenreEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "Movie")
@Table(name = "Movies")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity extends BaseEntity {
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MovieGenreEnum genre;
    @NotNull
    @Min(0)
    private short allowedAge;
    @NotNull
    @Min(0)
    private short lengthMinutes;

}
