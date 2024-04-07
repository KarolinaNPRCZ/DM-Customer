package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.LoginRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
 class TestController {
    @PostMapping("/login-test1")
    BadCredentialsResponseDTO testEndpointTestValidation(@RequestBody LoginRequest loginRequest){
        if (loginRequest.userEmail().equals("testEmail")) {
            throw new BadCredentialsException("Wrong password") {};
        }
        throw new AuthenticationException("User with e-mail: " + loginRequest.userEmail() + " not found") {};
    }

    @PostMapping("/login-test2")
    BadCredentialsResponseDTO test2EndpointTestValidation(@RequestBody LoginRequest loginRequest){
        if (loginRequest.userEmail().equals("testEmail")) {
            throw new InternalAuthenticationServiceException("Authentication failed") {};
        }
        throw new AuthenticationException("Authentication failed") {};
    }
    }

