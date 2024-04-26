package com.ec.cinema.service;

import com.ec.cinema.domain.dto.booking.BookingDTO;


import java.util.List;

public interface BookingService extends CrudGeneratorService<BookingDTO,Long>{
    void disableSeatAndCancelBooking(Long seatId);
    List<BookingDTO> findBookingsByBillboardId(Long billboardId);


}
