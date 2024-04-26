package com.ec.cinema.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "Room")
@Table(name = "Rooms")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity extends BaseEntity{
    @NotNull
    @Size(max = 50)
    private String name;
    @NotNull
    @Min(0)
    private short number;
}
