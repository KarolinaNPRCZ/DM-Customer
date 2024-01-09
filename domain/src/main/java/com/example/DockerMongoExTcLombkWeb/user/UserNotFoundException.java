package com.example.DockerMongoExTcLombkWeb.user;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User with email: %s not found";

    public UserNotFoundException(String email) {
        super(MESSAGE.formatted(email));
    }
}
