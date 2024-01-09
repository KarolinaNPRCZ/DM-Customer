package com.example.DockerMongoExTcLombkWeb.error;

import com.example.DockerMongoExTcLombkWeb.user.UserEmailArledyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserControllerExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    UserAlreadyExistsResponse handle(UserEmailArledyExistsException exception) {

        return new UserAlreadyExistsResponse(
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

}
