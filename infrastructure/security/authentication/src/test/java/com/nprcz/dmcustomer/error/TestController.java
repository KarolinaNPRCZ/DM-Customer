package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.LoginRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
 class TestController {
    @PostMapping("/login")
    BadCredentialsResponseDTO testEndpointTestValidation(@RequestBody LoginRequest loginRequest){
        if (loginRequest.userEmail().equals("testEmail")) {
            throw new BadCredentialsException("Wrong password") {};
        }
        throw new AuthenticationException("User with e-mail: " + loginRequest.userEmail() + " not found") {};
    }
    }

