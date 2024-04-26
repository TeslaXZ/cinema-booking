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

import com.ec.cinema.domain.dto.room.RoomDTO;
import com.ec.cinema.service.impl.RoomServiceImpl;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
@Tag(name = "Controlador salas")
public class RoomController {

    private final RoomServiceImpl roomService;

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody @Valid RoomDTO roomDTO){
        RoomDTO createdRoom = roomService.create(roomDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdRoom.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms(){
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id){
        return ResponseEntity.ok(roomService.findById(id));
    } 
    @PutMapping
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody @Valid RoomDTO roomDTO){
        return ResponseEntity.ok(roomService.update(roomDTO)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoomDTO> deactiveRoom(@PathVariable Long id){
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
