package com.ec.cinema.repository;

import com.ec.cinema.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {

    @Query("SELECT b FROM Booking b " +
            "JOIN b.billboard bb " +
            "JOIN bb.movie m " +
            "WHERE m.genre = :genre " +
            "AND b.date BETWEEN :startDate AND :endDate")
    List<BookingEntity> findBookingsByGenreAndDateRange(@Param("genre") String genre,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate);

    List<BookingEntity> findBookingsByBillboardId(Long billboardId);
}
