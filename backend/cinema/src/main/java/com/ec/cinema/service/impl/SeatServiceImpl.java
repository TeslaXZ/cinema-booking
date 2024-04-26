package com.ec.cinema.service.impl;

import com.ec.cinema.domain.dto.seat.SeatDTO;
import com.ec.cinema.domain.entity.SeatEntity;
import com.ec.cinema.helper.mappers.SeatMapper;
import com.ec.cinema.repository.SeatRepository;
import com.ec.cinema.service.SeatService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        SeatEntity seat = seatRepository.findById(id).orElseThrow(()->  new NoSuchElementException("No seat whit id: " + id));
        return seatMapper.toSeatDto(seat);
    }

    @Override
    public SeatDTO  create(SeatDTO seat) {
        SeatEntity createdSeat = seatRepository.save(seatMapper.toSeat(seat));
        return seatMapper.toSeatDto(createdSeat);
        
    }

    @Override
    @Transactional
    public SeatDTO update(SeatDTO seatDTO) {
        SeatEntity seat = seatMapper.toSeat(findById(seatDTO.getId()));
            seat.setNumber(seatDTO.getNumber());
            seat.setRowNumber(seatDTO.getRowNumber());
            seatRepository.save(seat);
            return seatMapper.toSeatDto(seat);
        
    }

    @Override
    @Transactional
    public void delete(Long id) {
        SeatEntity seat = seatMapper.toSeat(findById(id));
        seat.setStatus(false);
        
    }
}
