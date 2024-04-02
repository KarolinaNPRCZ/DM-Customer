package com.nprcz.dmcustomer.error;

import org.springframework.http.HttpStatus;

public record ProductAlreadyExistsResponse(
        String description,
        HttpStatus httpStatus
) {

}
