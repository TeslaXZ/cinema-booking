package com.ec.cinema.domain.dto.booking;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ec.cinema.domain.dto.BaseDTO;
import com.ec.cinema.domain.dto.billboard.BillboardDTO;
import com.ec.cinema.domain.dto.customer.CustomerDTO;
import com.ec.cinema.domain.dto.seat.SeatDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(callSuper = false)
public class BookingDTO extends BaseDTO{
    @NotNull(message = "date no puede ser nulo")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDate date;
    @NotNull(message = "customer no puede ser nulo")
    private CustomerDTO customer;
    @NotNull(message = "seat no puede ser nulo")
    private SeatDTO seat;
    @NotNull(message = "billboard no puede ser nulo")
    private BillboardDTO billboard;


}
