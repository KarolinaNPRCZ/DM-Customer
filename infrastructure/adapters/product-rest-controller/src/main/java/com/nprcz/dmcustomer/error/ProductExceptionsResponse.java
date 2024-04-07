package com.nprcz.dmcustomer.error;

import org.springframework.http.HttpStatus;

public record ProductExceptionsResponse(
        String description,
        HttpStatus httpStatus
) {

}
