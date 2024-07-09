package com.example.myphase.entity;

import com.example.myphase.enums.Mood;
import com.example.myphase.enums.CyclePhase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserDailyTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackerId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Mood mood;

    @Enumerated(EnumType.STRING)
    private CyclePhase cyclePhase;

    private LocalDateTime date;

}