package com.example.netdive.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "REPLY")
public class Reply {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long replyId;

    private String userId;

    private LocalDateTime regTime;

    private String content;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ChildReply> childReplyList;

}
