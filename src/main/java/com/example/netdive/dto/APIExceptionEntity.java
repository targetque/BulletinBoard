package com.example.netdive.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class APIExceptionEntity {
    private String code;
    private String message;
}
