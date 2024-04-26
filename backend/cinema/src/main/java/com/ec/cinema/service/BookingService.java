package com.ec.cinema.service;

import com.ec.cinema.domain.entity.BookingEntity;

import java.util.List;

public interface BookingService extends CrudGeneratorService<BookingEntity,Long>{
    void disableSeatAndCancelBooking(Long seatId);
    List<BookingEntity> findBookingsByBillboardId(Long billboardId);


}
