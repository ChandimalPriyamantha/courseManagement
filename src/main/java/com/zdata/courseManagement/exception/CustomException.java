package com.zdata.courseManagement.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final HttpStatus status; // HTTP status code for the exception

    public CustomException(String message, HttpStatus status) { // Constructor to accept a custom message
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() { // Getter for the HTTP status code
        return status;
    }

}
