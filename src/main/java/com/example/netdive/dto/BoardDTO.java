package com.example.netdive.dto;

import com.example.netdive.model.Board;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    private String userId;
    private String title;
    private String content;

    /*public String getUserId() {
        return userId;
    }*/

}
