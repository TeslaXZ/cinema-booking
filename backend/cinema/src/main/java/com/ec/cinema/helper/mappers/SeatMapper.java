package com.ec.cinema.helper.mappers;

import com.ec.cinema.domain.dto.seat.SeatDTO;
import com.ec.cinema.domain.entity.SeatEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface SeatMapper {
    SeatEntity toSeat(SeatDTO roomDto);

    SeatDTO toSeatDto(SeatEntity room);
}
