package com.zdata.courseManagement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle custom exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Handle validation exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return ResponseEntity.internalServerError().body("An unexpected error occurred: " + ex.getMessage());
    }

}
