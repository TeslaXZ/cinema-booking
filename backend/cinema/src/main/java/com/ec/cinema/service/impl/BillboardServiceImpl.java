package com.ec.cinema.service.impl;

import com.ec.cinema.domain.dto.billboard.BillboardDTO;
import com.ec.cinema.domain.entity.BillboardEntity;
import com.ec.cinema.domain.entity.BookingEntity;
import com.ec.cinema.domain.entity.CustomerEntity;
import com.ec.cinema.domain.entity.SeatEntity;
import com.ec.cinema.exception.InvalidBillboardCancellationException;
import com.ec.cinema.helper.mappers.BillboardMapper;
import com.ec.cinema.helper.mappers.BookingMapper;
import com.ec.cinema.helper.mappers.MovieMapper;
import com.ec.cinema.repository.BillboardRepository;
import com.ec.cinema.service.BillboardService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BillboardServiceImpl implements BillboardService {

        private final BookingServiceImpl bookingService;
        private final SeatServiceImpl seatService;
        private final BillboardRepository billboardRepository;
        private final BillboardMapper billboardMapper;
        private final BookingMapper bookingMapper;
        private final MovieMapper movieMapper;

    @Override
    @Transactional
    public void cancelBillboardAndBookings(Long billboardId) {
        // Validación de la fecha de la cartelera
        cancelBillboardValidation(billboardId);

        List<BookingEntity> bookings = bookingService.findBookingsByBillboardId(billboardId).stream().map(bookingMapper :: toBooking).toList();
        for(BookingEntity booking : bookings){
            SeatEntity seat = booking.getSeat();
            CustomerEntity customer = booking.getCustomer();
            BillboardEntity billboard = billboardRepository.findById(billboardId).orElseThrow(() -> new NoSuchElementException("No Billboard whit id: "+ billboardId));

            bookingService.delete(booking.getId());
            seatService.delete(seat.getId());
            billboard.setStatus(false);
            
           System.out.println("Customers affectes: "+ customer.getName() + " " + customer.getLastname());
              
        }

    
    }

    @Override
    public void cancelBillboardValidation(Long billboardId) {
            BillboardEntity billboard =  billboardMapper.toBillboard(findById(billboardId));
            if(billboard.getDate().isBefore(LocalDate.now())){
                throw new InvalidBillboardCancellationException("No se puede cancelar funciones de la cartelera con fecha anterior a la actual");
            }
    }

    @Override
    public List<BillboardDTO> findAll() {
        return billboardRepository.findAll().stream().map(billboardMapper:: toBillboardDto).toList();
    }

    @Override
    public BillboardDTO findById(Long id) {
        BillboardEntity billboard = billboardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Billboard whit id: "+id));
        return billboardMapper.toBillboardDto(billboard);
    }

    @Override
    public BillboardDTO create(BillboardDTO billboardDTO) {
        BillboardEntity billboardCreated = billboardRepository.save(billboardMapper.toBillboard(billboardDTO));
        return billboardMapper.toBillboardDto(billboardCreated);
    }

    @Override
    @Transactional
    public BillboardDTO update(BillboardDTO billboardDTO) {
        BillboardEntity billboard = billboardRepository.findById(billboardDTO.getId()).orElseThrow(() -> new NoSuchElementException("No Billboard whit id: "+ billboardDTO.getId()));
        billboard.setDate(billboardDTO.getDate());
        billboard.setStartTime(billboardDTO.getStartTime());
        billboard.setEndTime(billboardDTO.getEndTime());
        billboard.setMovie(movieMapper.toMovie(billboardDTO.getMovie()));
        return billboardMapper.toBillboardDto(billboard);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        BillboardEntity billboard = billboardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Billboard whit id: "+ id));
        billboard.setStatus(false);
    }
}
