package com.ec.cinema.service.impl;

import com.ec.cinema.domain.entity.BillboardEntity;
import com.ec.cinema.domain.entity.BookingEntity;
import com.ec.cinema.domain.entity.CustomerEntity;
import com.ec.cinema.domain.entity.SeatEntity;
import com.ec.cinema.exception.InvalidBillboardCancellationException;
import com.ec.cinema.repository.BillboardRepository;
import com.ec.cinema.service.BillboardService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BillboardServiceImpl implements BillboardService {

        private final BookingServiceImpl bookingService;
        private final SeatServiceImpl seatService;
        private final BillboardRepository billboardRepository;

    @Override
    @Transactional
    public void cancelBillboardAndBookings(Long billboardId) {
        // Validación de la fecha de la cartelera
        cancelBillboardValidation(billboardId);
        //Lista de reservas de esta cartelera
        List<BookingEntity> bookings = bookingService.findBookingsByBillboardId(billboardId);
        for(BookingEntity booking : bookings){
            SeatEntity seat = booking.getSeat();
            CustomerEntity customer = booking.getCustomer();
            //Imprime en la consola los clientes afectados por esta cancelacion
            System.out.println("Cliente afectado: " + customer.getName() + " " + customer.getLastname());
            //Se cancela la reserva con safe delete para que igualmente quede registro en la bd
            bookingService.delete(booking.getId());
            //Se desactiva la butaca para que pueda volver a ser tomada
            seatService.delete(seat.getId());

        }
    }

    @Override
    public void cancelBillboardValidation(Long billboardId) {
            BillboardEntity billboard = findById(billboardId);
            if(billboard.getDate().isBefore(LocalDate.now())){
                throw new InvalidBillboardCancellationException("No se puede cancelar funciones de la cartelera con fecha anterior a la actual");
            }
    }

    @Override
    public List<BillboardEntity> findAll() {
        return null;
    }

    @Override
    public BillboardEntity findById(Long id) {
        return billboardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Billboard not found"));
    }

    @Override
    public BillboardEntity create(BillboardEntity billboardEntity) {
        return null;
    }

    @Override
    public BillboardEntity update(BillboardEntity billboardEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
