package com.ec.cinema.service.impl;

import com.ec.cinema.domain.entity.BookingEntity;
import com.ec.cinema.domain.entity.SeatEntity;
import com.ec.cinema.repository.BookingRepository;
import com.ec.cinema.service.BookingService;
import com.ec.cinema.service.SeatService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final SeatServiceImpl seatService;
    private final BookingRepository bookingRepository;

    @Override
    public List<BookingEntity> findAll() {
        return null;
    }

    @Override
    public BookingEntity findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Booking not found"));
    }

    @Override
    public BookingEntity create(BookingEntity bookingEntity) {
        return null;
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        BookingEntity booking = findById(id);
        booking.setStatus(false);
    }

    @Override
    public void disableSeatAndCancelBooking(Long bookingId) {
        BookingEntity booking = findById(bookingId);
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
    public List<BookingEntity> findBookingsByBillboardId(Long billboardId) {
        return bookingRepository.findBookingsByBillboardId(billboardId);
    }
}
