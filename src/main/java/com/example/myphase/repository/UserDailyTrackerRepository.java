package com.example.myphase.repository;

import com.example.myphase.entity.UserDailyTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDailyTrackerRepository extends JpaRepository<UserDailyTracker, Long> {
}