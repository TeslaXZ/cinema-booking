package com.ec.cinema.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ec.cinema.domain.entity.BookingEntity;
import com.ec.cinema.domain.entity.SeatEntity;
import com.ec.cinema.repository.BookingRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private SeatServiceImpl seatService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDisableSeatAndCancelBooking() {
    // Arrange
    Long bookingId = 1L;
    Long seatId = 1L;

    BookingEntity booking = new BookingEntity();
    booking.setId(bookingId);
    booking.setStatus(true);
    SeatEntity seat = new SeatEntity();
    seat.setId(seatId);
    seat.setStatus(true);
    booking.setSeat(seat);

    // Configurar el mock de bookingRepository para devolver la reserva
    when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

    // Act
    bookingService.disableSeatAndCancelBooking(bookingId);

    // Assert
    // Verifica que la reserva se deshabilita cambiando su estado a false
    assertFalse(booking.getStatus());
    verify(seatService, times(1)).delete(booking.getSeat().getId());
    }

    @Test
    void testDisableSeatAndCancelBookingSeatNotFound() {
        Long bookingId = 1L;
        BookingEntity booking = new BookingEntity();
        booking.setId(bookingId);
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        assertThrows(EntityNotFoundException.class, () -> bookingService.disableSeatAndCancelBooking(bookingId));
}
}