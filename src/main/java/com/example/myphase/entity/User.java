package com.example.myphase.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
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

// Getters and Setters
