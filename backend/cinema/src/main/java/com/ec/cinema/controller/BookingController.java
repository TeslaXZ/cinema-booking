package com.ec.cinema.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ec.cinema.domain.dto.booking.BookingDTO;
import com.ec.cinema.domain.enums.MovieGenreEnum;
import com.ec.cinema.service.impl.BookingServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
@Tag(name = "Controlador reservas")
public class BookingController {

    private final BookingServiceImpl bookingService;


    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody @Valid BookingDTO BookingDTO){
        BookingDTO createdRoom = bookingService.create(BookingDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdRoom.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings(){
        return ResponseEntity.ok(bookingService.findAll());
    }

    @GetMapping("/getBygenreAndDates")
    public ResponseEntity<List<BookingDTO>> getbyGenreAndDates(@RequestParam MovieGenreEnum category , @RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return ResponseEntity.ok(bookingService.findBookingsByGenreAndDateRange(category, startDate, endDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.findById(id));
    } 
    @PutMapping
    public ResponseEntity<BookingDTO> updateBooking(@RequestBody @Valid BookingDTO BookingDTO){
        return ResponseEntity.ok(bookingService.update(BookingDTO)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingDTO> deactiveBooking(@PathVariable Long id){
        bookingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "cancelBooking")
    public ResponseEntity<Void> CancelBookingAnddisableSeat(@RequestParam Long bookingId){
        bookingService.disableSeatAndCancelBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
