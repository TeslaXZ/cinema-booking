package com.ec.cinema.domain.dto.room;

import com.ec.cinema.domain.dto.BaseDTO;
import com.ec.cinema.domain.entity.RoomEntity;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class RoomDTO extends BaseDTO {
    @NotEmpty
    @Size(max = 50, min = 1)
    private String name;
    @NotNull
    @Min(value = 1)
    private Short number;
}
