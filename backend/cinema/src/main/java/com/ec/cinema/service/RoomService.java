package com.ec.cinema.service;


import java.util.List;

import com.ec.cinema.domain.dto.room.RoomDTO;
import com.ec.cinema.domain.dto.room.RoomOccupancyDTO;

public interface RoomService extends CrudGeneratorService<RoomDTO, Long> {
    List<RoomOccupancyDTO> findAvailableAndOccupiedSeatsByRoom();
}
