package com.example.myphase.service;

import com.example.myphase.dto.UserRegistrationDto;
import com.example.myphase.entity.User;
import com.example.myphase.factory.UserFactory;
import com.example.myphase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userFactory = userFactory;
    }

    public User registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = userFactory.createUser(userRegistrationDto);

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        user.setPassword(encodedPassword);

        // Save the user to the database
        return userRepository.save(user);
    }
}