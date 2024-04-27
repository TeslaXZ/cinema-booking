package com.ec.cinema.service.impl;

import com.ec.cinema.domain.dto.room.RoomDTO;
import com.ec.cinema.domain.dto.room.RoomOccupancyDTO;
import com.ec.cinema.domain.entity.RoomEntity;
import com.ec.cinema.helper.mappers.RoomMapper;
import com.ec.cinema.repository.RoomRepository;
import com.ec.cinema.service.RoomService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService  {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    
    @Override
    public List<RoomDTO> findAll() {
        return roomRepository.findAll().stream().map(roomMapper :: toRoomDto).toList();
    }

    @Override
    public RoomDTO findById(Long id) {
        RoomEntity room = roomRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No room whit id: " + id));
        return roomMapper.toRoomDto(room);
    }

    @Override
    public RoomDTO create(RoomDTO roomDTO) {
        System.out.println(roomDTO);
        RoomEntity CreatedRoom = roomRepository.save(new RoomEntity(roomDTO.getName(), roomDTO.getNumber()));
        return roomMapper.toRoomDto(CreatedRoom);
    }

    @Override
    @Transactional
    public RoomDTO update(RoomDTO roomDTO) {
        RoomEntity room = roomRepository.findById(roomDTO.getId()).orElseThrow(()-> new NoSuchElementException("No room whit id: " + roomDTO.getId()));
            room.setName(roomDTO.getName());
            room.setNumber(roomDTO.getNumber());
            return roomMapper.toRoomDto(room);
        

    }

    @Override
    @Transactional
    public void delete(Long id) {
        RoomEntity room = roomRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No room whit id: " + id));
        room.setStatus(false);
    }

    @Override
    public List<RoomOccupancyDTO> findAvailableAndOccupiedSeatsByRoom() {
      
        List<Object[]> availableAndOccupiedSeats = roomRepository.findAvailableAndOccupiedSeatsByRoom(LocalDate.now());
        return availableAndOccupiedSeats.stream()
            .map(availableAndOccupiedSeat -> new RoomOccupancyDTO(
                (Long) availableAndOccupiedSeat[0], 
                (String) availableAndOccupiedSeat[1], 
                (Long) availableAndOccupiedSeat[2], 
                (Long) availableAndOccupiedSeat[3] 
            ))
            .collect(Collectors.toList());
    }
}
