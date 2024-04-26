package com.ec.cinema.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Test
    void testFindAvailableAndOccupiedSeatsByRoom() {
        List<Object[]> results = roomRepository.findAvailableAndOccupiedSeatsByRoom(LocalDate.now());
        assertNotNull(results);
        for (Object[] result : results) {
            String roomName = (String) result[0];
            long availableSeats = (long) result[1];
            long occupiedSeats = (long) result[2];

            // Se verifica los valores obtenidos para cada sala
            assertNotNull(roomName);
            assertTrue(availableSeats >= 0);
            assertTrue(occupiedSeats >= 0);
        }

    }
}
