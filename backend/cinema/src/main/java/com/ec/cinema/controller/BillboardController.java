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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ec.cinema.domain.dto.billboard.BillboardDTO;
import com.ec.cinema.service.impl.BillboardServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v1/billboard")
@RequiredArgsConstructor
@Tag(name = "Controlador cartelera")
public class BillboardController {

    private final BillboardServiceImpl billboardService;

    @PostMapping
    public ResponseEntity<BillboardDTO> createBillboard(@RequestBody @Valid BillboardDTO BillboardDTO){
        BillboardDTO createdRoom = billboardService.create(BillboardDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdRoom.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<BillboardDTO>> getAllBillboards(){
        return ResponseEntity.ok(billboardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillboardDTO> getBillboardById(@PathVariable Long id){
        return ResponseEntity.ok(billboardService.findById(id));
    } 
    @PutMapping
    public ResponseEntity<BillboardDTO> updateBillboard(@RequestBody @Valid BillboardDTO BillboardDTO){
        return ResponseEntity.ok(billboardService.update(BillboardDTO)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BillboardDTO> deactiveBillboard(@PathVariable Long id){
        billboardService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cancelBillboardAndBookings")
    public ResponseEntity<Void> cancelBillboardAndBookings(@RequestParam Long id){
        billboardService.cancelBillboardAndBookings(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
