package com.ec.cinema.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.ec.cinema.domain.dto.movie.MovieDTO;
import com.ec.cinema.domain.entity.MovieEntity;
import com.ec.cinema.helper.mappers.MovieMapper;
import com.ec.cinema.repository.MovieRepository;
import com.ec.cinema.service.MovieService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    
    
    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream().map(movieMapper:: toMovieDto).toList();
    }

    @Override
    public MovieDTO findById(Long id) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No movie whith id: " + id));
        return movieMapper.toMovieDto(movie);
    }

    @Override
    public MovieDTO create(MovieDTO movieDTO) {
       MovieEntity movieCreated = movieRepository.save(movieMapper.toMovie(movieDTO));
       return movieMapper.toMovieDto(movieCreated);
    }

    @Override
    @Transactional
    public MovieDTO update(MovieDTO movieDTO) {
        MovieEntity movie = movieRepository.findById(movieDTO.getId()).orElseThrow(()-> new NoSuchElementException("No movie whith id: " + movieDTO.getId()));
        movie.setName(movieDTO.getName());
        movie.setGenre(movieDTO.getGenre());
        movie.setAllowedAge(movieDTO.getAllowedAge());
        movie.setLengthMinutes(movieDTO.getLengthMinutes());
        return movieMapper.toMovieDto(movie);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No movie whith id: " + id));
      movie.setStatus(false);

    }
}
