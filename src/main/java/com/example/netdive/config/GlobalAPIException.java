package com.example.netdive.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalAPIException {

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Object> handleCustomException() {
        return handleCustomException();
    }

}
