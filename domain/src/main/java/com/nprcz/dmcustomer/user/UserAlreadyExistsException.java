package com.nprcz.dmcustomer.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String format) {
    super(format);
    }
}
