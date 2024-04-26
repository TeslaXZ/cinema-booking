package com.ec.cinema.service.impl;

import com.ec.cinema.domain.dto.booking.BookingDTO;
import com.ec.cinema.domain.entity.BookingEntity;
import com.ec.cinema.domain.entity.SeatEntity;
import com.ec.cinema.domain.enums.MovieGenreEnum;
import com.ec.cinema.helper.mappers.BookingMapper;
import com.ec.cinema.repository.BookingRepository;
import com.ec.cinema.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final SeatServiceImpl seatService;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public List<BookingDTO> findAll() {
        return bookingRepository.findAll().stream().map(bookingMapper:: toBookingDto).toList();
    }

    @Override
    public BookingDTO findById(Long id) {
       BookingEntity booking = bookingRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No booking whit id: " + id));
       return bookingMapper.toBookingDto(booking);
    }

    @Override
    public BookingDTO create(BookingDTO bookingDTO) {
        BookingEntity bookingCreated = bookingRepository.save(bookingMapper.toBooking(bookingDTO));
        return bookingMapper.toBookingDto(bookingCreated);
    }

    @Override
    @Transactional
    public BookingDTO update(BookingDTO bookingDTO) {
        BookingEntity booking = bookingRepository.findById(bookingDTO.getId()).orElseThrow(()-> new NoSuchElementException("No booking whit id: " + bookingDTO.getId()));
        booking.setDate(bookingDTO.getDate());
        booking.setSeat(booking.getSeat());
        return bookingMapper.toBookingDto(booking);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        BookingEntity booking = bookingRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No booking whit id: " + id));
        booking.setStatus(false);
        
    }

    @Override
    @Transactional
    public void disableSeatAndCancelBooking(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(()-> new NoSuchElementException("No booking whit id: " + bookingId));
        SeatEntity seat = booking.getSeat();
        if(seat == null){
            throw new EntityNotFoundException("Seat not found");
        }
        //cancelar reserva
        delete(bookingId);
        //Inhabilitar la butaca
        seatService.delete(seat.getId());
    }

    @Override
    public List<BookingDTO> findBookingsByBillboardId(Long billboardId) {
        List<BookingEntity> bookingList = bookingRepository.findBookingsByBillboardId(billboardId);
         return bookingList.stream().map(bookingMapper :: toBookingDto).toList();
    }

    @Override
    public List<BookingDTO> findBookingsByGenreAndDateRange(MovieGenreEnum movieCategory, LocalDate starDate,
            LocalDate endDate) {
                
        return bookingRepository.findBookingsByGenreAndDateRange(movieCategory, starDate, endDate)
                                            .stream().map(bookingMapper:: toBookingDto).toList();
    }
}
