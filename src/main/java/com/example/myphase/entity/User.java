package com.example.myphase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private UUID userId;

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String country;
    private LocalDate lastLogin;

}