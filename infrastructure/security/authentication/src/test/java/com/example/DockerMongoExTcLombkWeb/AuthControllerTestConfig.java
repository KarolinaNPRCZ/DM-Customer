package com.example.DockerMongoExTcLombkWeb;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AuthControllerTestConfig {
    @Bean
    AuthController authController(JwtAuthenticator jwtAuthenticator) {
        return new AuthController(jwtAuthenticator);
    }

    @Bean
    JwtAuthenticator jwtAuthenticator() {
        return Mockito.mock(JwtAuthenticator.class);
    }

}