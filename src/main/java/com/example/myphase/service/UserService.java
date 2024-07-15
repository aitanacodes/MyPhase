package com.example.myphase.service;

import com.example.myphase.dto.UserRegistrationDto;
import com.example.myphase.dto.UserUpdateDto;
import com.example.myphase.entity.User;
import com.example.myphase.exceptions.ResourceNotFoundException;
import com.example.myphase.factory.UserFactory;
import com.example.myphase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.myphase.constants.ErrorMessage;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND_MSG = "User not found";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFactory userFactory;

    public User registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = userFactory.createUser(userRegistrationDto);
        String encodedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User getUserByUuid(String uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MSG));
    }

    public void deleteUserByUuid(String uuid) {
        userRepository.findById(uuid).ifPresentOrElse(
                user -> userFactory.deleteUser(uuid),
                () -> {
                    throw new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MSG);
                }
        );
    }

    public User updateUser(String uuid, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MSG));

        return userFactory.updateUser(existingUser, userUpdateDto);
    }

    public void updateUserPassword(String uuid, String newPassword) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND_MSG));

        userFactory.updatePassword(user, newPassword);
    }
}