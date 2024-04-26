package com.ec.cinema.service;

import com.ec.cinema.domain.dto.booking.BookingDTO;
import com.ec.cinema.domain.enums.MovieGenreEnum;

import java.time.LocalDate;
import java.util.List;

public interface BookingService extends CrudGeneratorService<BookingDTO,Long>{
    void disableSeatAndCancelBooking(Long seatId);
    List<BookingDTO> findBookingsByBillboardId(Long billboardId);
    List<BookingDTO> findBookingsByGenreAndDateRange(MovieGenreEnum movieCategory, LocalDate starDate, LocalDate endDate);

}
