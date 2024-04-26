package com.ec.cinema.domain.dto.room;

import com.ec.cinema.domain.dto.BaseDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO extends BaseDTO {
    @NotBlank
    @Size(max = 50, min = 1)
    private String name;
    @NotNull
    @Min(value = 1)
    private Short number;
}
