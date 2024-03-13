package com.example.netdive.dto;

import lombok.Builder;
import lombok.Setter;

@Builder
public class CommonErrorInfo {
    private String errorMessage;
    private int errorCoce;
}
