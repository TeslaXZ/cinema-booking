package com.ec.cinema.service.impl;

import com.ec.cinema.domain.dto.room.RoomDTO;
import com.ec.cinema.domain.entity.RoomEntity;
import com.ec.cinema.helper.mappers.RoomMapper;
import com.ec.cinema.repository.RoomRepository;
import com.ec.cinema.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService  {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    @Override
    public List<RoomDTO> findAll() {
        return null;
    }

    @Override
    public RoomDTO findById(Long id) {
        RoomEntity room = roomRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Room not found"));
        return roomMapper.toRoomDto(room);
    }

    @Override
    public RoomDTO create(RoomDTO roomDTO) {
        return null;
    }

    @Override
    public RoomDTO update(RoomDTO roomDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
