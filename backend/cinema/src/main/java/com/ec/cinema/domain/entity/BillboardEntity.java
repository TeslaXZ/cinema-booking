package com.ec.cinema.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Billboard")
@Table(name = "Billboard")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BillboardEntity extends BaseEntity{
    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

}
