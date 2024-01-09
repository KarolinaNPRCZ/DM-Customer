package com.example.DockerMongoExTcLombkWeb.error;

import org.springframework.http.HttpStatus;

public record UserAlreadyExistsResponse(
        String description,
        HttpStatus httpStatus
) {

}
