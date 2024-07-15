package com.example.myphase.factory;


import com.example.myphase.dto.UserRegistrationDto;
import com.example.myphase.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class UserFactory {

    public User createUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setCountry(userRegistrationDto.getCountry());

        // Convert birthDate from String to EpochMilli
        String birthDateString = userRegistrationDto.getBirthDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        user.setBirthDate(birthDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());

        return user;
    }
}