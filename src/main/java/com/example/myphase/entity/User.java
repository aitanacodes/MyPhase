package com.example.myphase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String country;
    private LocalDate lastLogin;
    private String password;

    @PrePersist
    protected void onCreate() {
        if (userId == null) {
            userId = UUID.randomUUID().toString(); // Generar UUID como String
        }
    }
}