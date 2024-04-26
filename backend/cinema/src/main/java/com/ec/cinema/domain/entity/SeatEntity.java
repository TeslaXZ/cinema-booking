package com.ec.cinema.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "Seat")
@Table(name = "seats")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SeatEntity extends BaseEntity {
    @NotNull
    @Min(0)
    private short number;
    @NotNull
    @Min(0)
    private short rowNumber;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
}
