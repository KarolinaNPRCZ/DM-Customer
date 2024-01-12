package com.example.DockerMongoExTcLombkWeb.user;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User with e-mail: %s not found";

    public UserNotFoundException(String email) {
        super(MESSAGE.formatted(email));
    }
}
