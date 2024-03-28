package com.omoniyi24.chatapp.entity;

import com.omoniyi24.chatapp.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String name;
    private Instant timeCreated;
}
