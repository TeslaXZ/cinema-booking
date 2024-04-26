package com.ec.cinema.helper.mappers;

import com.ec.cinema.domain.dto.room.RoomDTO;
import com.ec.cinema.domain.entity.RoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomEntity toRoom(RoomDTO roomDTO);
    RoomDTO toRoomDto(RoomEntity room);

}
