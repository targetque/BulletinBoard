package com.example.netdive.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    NOT_FOUND("404","요청한 자원이 존재하지 않습니다.");
    private String code;
    private String message;
    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
