package com.ec.cinema.controller;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.time.LocalDate;
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

import com.ec.cinema.domain.dto.seat.SeatDTO;
import com.ec.cinema.service.impl.SeatServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/seats")
@RequiredArgsConstructor
@Tag(name = "Controlador butacas")
public class SeatController {

    private final SeatServiceImpl seatService;

    @PostMapping(value = "save")
    public ResponseEntity<SeatDTO> createSeat(@RequestBody @Valid SeatDTO seatDTO){
        SeatDTO createdSeat = seatService.create(seatDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdSeat.getId()).toUri();
        return ResponseEntity.created(location).build(); 
    }

    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeats(){
        return ResponseEntity.ok(seatService.findAll()); 
    }
    @GetMapping("/{id}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable Long id){
        return ResponseEntity.ok(seatService.findById(id));
    } 
    @PutMapping
    public ResponseEntity<SeatDTO> updateSeat(@RequestBody @Valid SeatDTO seatDTO){
        return ResponseEntity.ok(seatService.update(seatDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SeatDTO> deactiveSeat(@PathVariable Long id){
        seatService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
