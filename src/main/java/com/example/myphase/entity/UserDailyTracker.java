package com.example.myphase.entity;

package com.example.myphase.entity;

import jakarta.persistence.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    // Getters and Setters
}

enum Mood {
    GOOD, TIRED, AVERAGE
}

enum CyclePhase {
    MENSTRUATION, FOLLICULAR, OVULATORY, LUTEAL, ISCHEMIC
}
