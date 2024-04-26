package com.ec.cinema.service.impl;

import com.ec.cinema.domain.dto.seat.SeatDTO;
import com.ec.cinema.domain.entity.SeatEntity;
import com.ec.cinema.helper.mappers.SeatMapper;
import com.ec.cinema.repository.SeatRepository;
import com.ec.cinema.service.SeatService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    @Override
    public List<SeatDTO> findAll() {
        return seatRepository.findAll().stream().map(seatMapper:: toSeatDto ).toList();
    }

    @Override
    public SeatDTO findById(Long id) {
        SeatEntity seat = seatRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Seat not found"));
        return seatMapper.toSeatDto(seat);
    }

    @Override
    public SeatDTO  create(SeatDTO seat) {
        SeatEntity createdSeat = seatRepository.save(seatMapper.toSeat(seat));
        return seatMapper.toSeatDto(createdSeat);
        
    }

    @Override
    public SeatDTO update(SeatDTO seatEntity) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        SeatEntity seat = seatRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Seat not found"));;
        seat.setStatus(false);
    }
}
