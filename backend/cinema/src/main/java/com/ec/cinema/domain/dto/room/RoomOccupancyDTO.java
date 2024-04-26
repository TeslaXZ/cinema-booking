package com.ec.cinema.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomOccupancyDTO {
    private String roomName;
    private long availableSeats;
    private long occupiedSeats;
}
