package com.ec.cinema.controller;

import com.ec.cinema.domain.dto.error.ErrorResponseDto;
import com.ec.cinema.exception.InvalidBillboardCancellationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorResponseDto> errors = ex.getFieldErrors().stream().map(ErrorResponseDto::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBillboardCancellationException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(InvalidBillboardCancellationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

   
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException( NoSuchElementException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        String originalMsg = ex.getMessage();
        String duplicateValue = ObtainDuplicateValue(originalMsg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(duplicateValue + " is not available");
    }

    private String ObtainDuplicateValue(String msg) {
        String begin = "Duplicate entry '";
        String end = "' for key";
        int indexStart = msg.indexOf(begin) + begin.length();
        int indexEnd = msg.indexOf(end, indexStart);
        return msg.substring(indexStart, indexEnd);
    }
}
