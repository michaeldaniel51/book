package com.danny.book.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> bookException(RuntimeException runtimeException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(runtimeException.getLocalizedMessage());
        errorResponse.setTimeStamp(LocalDateTime.now().toString());
        errorResponse.setDetails("an error happened");
        errorResponse.setTitle("Runtime Exception");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);


    }
}