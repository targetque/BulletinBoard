package com.example.netdive.config;

import com.example.netdive.dto.CommonErrorInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public CommonErrorInfo forbiddenError() {
        CommonErrorInfo commonErrorInfo = CommonErrorInfo
                .builder()
                .errorCoce(403)
                .errorMessage("허가되지 않은 접근입니다.")
                .build();
        return commonErrorInfo;
    }

}
