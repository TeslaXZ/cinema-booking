package com.ec.cinema.repository;

import com.ec.cinema.domain.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository <SeatEntity, Long> {

	@Query("SELECT s.room.name AS roomName, " +
            "SUM(CASE WHEN b IS NULL THEN 1 ELSE 0 END) AS availableSeats, " +
            "SUM(CASE WHEN b IS NOT NULL THEN 1 ELSE 0 END) AS occupiedSeats " +
            "FROM Seat s " +
            "JOIN s.room r " +
            "LEFT JOIN Booking b ON b.seat = s AND b.date = :currentDate " +
            "GROUP BY r.name")
List<Object[]> findAvailableAndOccupiedSeatsByRoom(@Param("currentDate") LocalDate currentDate);

}
