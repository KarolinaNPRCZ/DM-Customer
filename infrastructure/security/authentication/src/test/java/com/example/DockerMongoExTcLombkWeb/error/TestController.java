package com.example.DockerMongoExTcLombkWeb.error;

import com.example.DockerMongoExTcLombkWeb.LoginRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/login")
    BadCredentialsResponseDTO testEndpointTestValidation(@RequestBody LoginRequest loginRequest){
        if (loginRequest.userEmail().equals("testEmail")) {
            throw new BadCredentialsException("Wrong password") {};
        }
        throw new AuthenticationException("User with e-mail: " + loginRequest.userEmail() + " not found") {};
    }
    }

