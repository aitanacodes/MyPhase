package com.example.myphase.factory;


import com.example.myphase.dto.UserRegistrationDto;
import com.example.myphase.dto.UserUpdateDto;
import com.example.myphase.entity.User;
import com.example.myphase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


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

    public void deleteUser(String uuid) {
        userRepository.deleteById(uuid);
    }

    public User updateUser(User existingUser, UserUpdateDto userUpdateDto) {
        existingUser.setFirstName(userUpdateDto.getFirstName());
        existingUser.setLastName(userUpdateDto.getLastName());
        existingUser.setEmail(userUpdateDto.getEmail());
        existingUser.setCountry(userUpdateDto.getCountry());

        LocalDate birthDate = LocalDate.parse(userUpdateDto.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        long epochMilli = birthDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        existingUser.setBirthDate(epochMilli);

        return userRepository.save(existingUser);
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}