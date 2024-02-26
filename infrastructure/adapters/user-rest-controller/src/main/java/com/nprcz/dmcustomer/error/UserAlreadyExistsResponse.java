package com.nprcz.dmcustomer.error;

import org.springframework.http.HttpStatus;

public record UserAlreadyExistsResponse(
        String description,
        HttpStatus httpStatus
) {

}
