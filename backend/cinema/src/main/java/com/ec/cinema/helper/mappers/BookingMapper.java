package com.ec.cinema.helper.mappers;

import org.mapstruct.Mapper;

import com.ec.cinema.domain.dto.booking.BookingDTO;
import com.ec.cinema.domain.entity.BookingEntity;

@Mapper(componentModel = "spring", uses = {SeatMapper.class, BillboardMapper.class, CustomerMapper.class})
public interface BookingMapper {

    BookingEntity toBooking(BookingDTO bookingDto);
    BookingDTO toBookingDto(BookingEntity booking);

}
