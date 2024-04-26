package com.ec.cinema.controller;
import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ec.cinema.domain.dto.movie.MovieDTO;
import com.ec.cinema.service.impl.MovieServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v1/movie")
@RequiredArgsConstructor
@Tag(name = "Controlador peliculas")
public class MovieController {

    private final MovieServiceImpl movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> createRoom(@RequestBody @Valid MovieDTO MovieDTO){
        MovieDTO createdRoom = movieService.create(MovieDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdRoom.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllRooms(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getRoomById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.findById(id));
    } 
    @PutMapping
    public ResponseEntity<MovieDTO> updateRoom(@RequestBody @Valid MovieDTO MovieDTO){
        return ResponseEntity.ok(movieService.update(MovieDTO)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDTO> deactiveRoom(@PathVariable Long id){
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
