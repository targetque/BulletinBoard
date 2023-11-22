package com.example.netdive.model;

import com.example.netdive.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String content;
    private String title;
    @JoinColumn(name = "user_id", nullable = false)
    private String userId;
    @CreationTimestamp
    private LocalDateTime regTime;
    private String fileSeq;

    @Builder
    public Board(BoardDTO dto) {
        userId = dto.getUserId();
        title = dto.getTitle();
        content = dto.getContent();
    }
    public void updateContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
