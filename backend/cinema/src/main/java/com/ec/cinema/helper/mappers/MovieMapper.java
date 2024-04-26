package com.ec.cinema.helper.mappers;

import org.mapstruct.Mapper;

import com.ec.cinema.domain.dto.movie.MovieDTO;
import com.ec.cinema.domain.entity.MovieEntity;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieEntity toMovie(MovieDTO movieDto);
	MovieDTO toMovieDto(MovieEntity movie);

}
