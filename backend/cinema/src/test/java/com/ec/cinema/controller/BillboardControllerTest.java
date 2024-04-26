package com.ec.cinema.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BillboardControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    @Transactional
    void testCancelBillboardAndBookings() {

        String url = "/api/v1/billboard/cancelBillboardAndBookings?id=1";

        ResponseEntity<Void> response = restTemplate.exchange(
            url,
            HttpMethod.DELETE,
            null,
            Void.class
        );
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
}
}