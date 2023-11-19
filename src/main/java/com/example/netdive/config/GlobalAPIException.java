package com.example.netdive.config;

import com.example.netdive.dto.APIExceptionEntity;
import com.example.netdive.enums.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalAPIException {

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Object> handleCustomException() {
        return handleCustomException();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<APIExceptionEntity> handleNoSuchElementException() {
        APIExceptionEntity exceptionEntity = APIExceptionEntity.builder().code("200").message(ResponseCode.NOT_FOUND.getMessage()).build();
        return new ResponseEntity(exceptionEntity, HttpStatus.NOT_FOUND);
    }

}
