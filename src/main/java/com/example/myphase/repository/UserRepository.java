package com.example.myphase.repository;

import com.example.myphase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}