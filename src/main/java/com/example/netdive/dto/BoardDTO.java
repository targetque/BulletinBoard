package com.example.netdive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private String userId;
    private String title;
    private String content;

    /*public String getUserId() {
        return userId;
    }*/

}
