package com.example.DockerMongoExTcLombkWeb.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String format) {
    super(format);
    }
}
