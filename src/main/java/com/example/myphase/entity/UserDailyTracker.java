package com.example.myphase.entity;

import com.example.myphase.enums.Mood;
import com.example.myphase.enums.CyclePhase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
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
    @Column(name = "cycle_phase")
    private CyclePhase cyclePhase;

    private LocalDateTime date;

}