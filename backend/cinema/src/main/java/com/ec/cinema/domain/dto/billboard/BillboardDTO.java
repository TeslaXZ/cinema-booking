package com.ec.cinema.domain.dto.billboard;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ec.cinema.domain.dto.BaseDTO;
import com.ec.cinema.domain.dto.movie.MovieDTO;
import com.ec.cinema.domain.dto.room.RoomDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BillboardDTO extends BaseDTO{
    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate date;
    @NotNull
    @DateTimeFormat(iso = ISO.TIME)
    private LocalTime startTime;
    @NotNull
    @DateTimeFormat(iso = ISO.TIME)
    private LocalTime endTime;
    @NotNull
    private MovieDTO movie;
    @NotNull
    private RoomDTO room;
}
