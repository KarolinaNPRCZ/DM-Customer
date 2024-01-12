package com.example.DockerMongoExTcLombkWeb;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Log4j2
class AuthController {
    //TODO Refactor Mappings
    private final JwtAuthenticator jwtAuthenticator;

    public AuthController(JwtAuthenticator jwtAuthenticator) {
        this.jwtAuthenticator = jwtAuthenticator;
    }


    @PostMapping("/user")
    ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = jwtAuthenticator.authenticate(request);
        return ResponseEntity.ok(loginResponse);
    }


}

