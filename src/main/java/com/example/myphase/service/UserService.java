package com.example.myphase.service;

import com.example.myphase.dto.UserRegistrationDto;
import com.example.myphase.entity.User;
import com.example.myphase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserRegistrationDto userRegistrationDto)  {
        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setCountry(userRegistrationDto.getCountry());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(userRegistrationDto.getBirthDate(), formatter);
        Long birthDateEpoch = birthDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        user.setBirthDate(birthDateEpoch);

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        user.setPassword(encodedPassword);

        // Save the user to the database
        return userRepository.save(user);
    }
}
