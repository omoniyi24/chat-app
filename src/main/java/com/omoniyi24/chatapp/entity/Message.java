package com.omoniyi24.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String content;
    private Long chatRoomId;
    private Instant timeCreated;
    private boolean deleted;

}
