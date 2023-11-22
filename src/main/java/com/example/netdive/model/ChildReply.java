package com.example.netdive.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "CHILD_REPLY")
public class ChildReply {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long childReplyId;
    private String userId;
    private LocalDateTime regTime;
    private String content;

}
