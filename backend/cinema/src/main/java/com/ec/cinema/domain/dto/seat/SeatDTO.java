package com.ec.cinema.domain.dto.seat;

import com.ec.cinema.domain.dto.BaseDTO;
import com.ec.cinema.domain.dto.room.RoomDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SeatDTO extends BaseDTO {
    @NotNull
    @Min(value = 1)
    private Short number;
    @NotNull
    @Min(value = 1)
    private Short rowNumber;
    @NotNull
    private RoomDTO room;
}
