package com.zdata.courseManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle custom exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("status", ex.getStatus().value());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Validation failed");

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                error.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(error);
    }

    // Handle validation exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Internal server error");
        error.put("message", ex.getMessage());
        error.put("status", 500);
        return ResponseEntity.status(500).body(error);
    }

}
