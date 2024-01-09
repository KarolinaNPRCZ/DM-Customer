package com.example.DockerMongoExTcLombkWeb.user;

public class UserEmailArledyExistsException extends RuntimeException {
    public UserEmailArledyExistsException(String format) {
    super(format);
    }
}
