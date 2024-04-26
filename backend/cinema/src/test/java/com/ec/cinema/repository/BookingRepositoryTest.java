package com.ec.cinema.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ec.cinema.domain.entity.BookingEntity;
import com.ec.cinema.domain.enums.MovieGenreEnum;


@SpringBootTest
public class BookingRepositoryTest {
  
    @Autowired
    private BookingRepository bookingRepository;
   

  

    @Test
    void testFindBookingsByGenreAndDateRange() {
        LocalDate startDate = LocalDate.parse("2024-07-01");
        LocalDate endDate = LocalDate.parse("2024-07-31");
        MovieGenreEnum genre = MovieGenreEnum.HORROR;

        // Llama al método que realiza la consulta
        List<BookingEntity> bookings = bookingRepository.findBookingsByGenreAndDateRange(genre, startDate, endDate);

        assertNotNull(bookings);
        for (BookingEntity booking : bookings) {
            assertEquals(genre, booking.getBillboard().getMovie().getGenre());
            assertTrue(booking.getDate().compareTo(startDate) >= 0 && booking.getDate().compareTo(endDate) <= 0);
        }
    }
    
}
