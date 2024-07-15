package com.example.myphase.constants;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    public static final String USER_NOT_FOUND_MSG = "User not found";

    private ErrorMessage() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
