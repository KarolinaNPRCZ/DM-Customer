package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.user.UserAlreadyExistsException;
import com.nprcz.dmcustomer.user.UserNotFoundException;
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
    UserAlreadyExistsResponse handle(UserAlreadyExistsException exception) {

        return new UserAlreadyExistsResponse(
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    UserAlreadyExistsResponse handle(UserNotFoundException exception) {

        return new UserAlreadyExistsResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

}
