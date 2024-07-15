package com.example.myphase.controller;

import com.example.myphase.dto.UserUpdateDto;
import com.example.myphase.entity.User;
import com.example.myphase.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/users")
@Tag(name = "User", description = "Endpoints for user management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getUser/{uuid}")
    @Operation(summary = "Get user by Uuid", description = "Retrieve a user by their unique Uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserByUuid(@PathVariable("uuid") String uuid) {
        User user = userService.getUserByUuid(uuid);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("deleteUser/{uuid}")
    @Operation(summary = "Delete user by Uuid", description = "Delete a user from the system by their unique Uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable String uuid) {
        userService.deleteUserByUuid(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/users/{uuid}")
    @Operation(summary = "Update user by Uuid", description = "Update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<User> updateUser(@PathVariable String uuid, @RequestBody UserUpdateDto updateUserDto) {
        User updatedUser = userService.updateUser(uuid, updateUserDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/api/users/{uuid}/password")
    @Operation(summary = "Update user password", description = "Change the password for a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<String> updateUserPassword(@PathVariable String uuid, @RequestBody String newPassword) {
        userService.updateUserPassword(uuid, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }
}
