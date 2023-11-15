package com.example.netdive.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userId;

    private LocalDateTime lastLogin;

    @OneToMany
    private List<Board> postList;

    private String password;


}
