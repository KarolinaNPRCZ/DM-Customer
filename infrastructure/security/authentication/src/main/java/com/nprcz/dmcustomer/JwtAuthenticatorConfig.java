package com.nprcz.dmcustomer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import java.time.Clock;


@Configuration
class JwtAuthenticatorConfig {

    @Bean
    JwtAuthenticator JwtAuthenticator(JWTConfigurationProperties jwtConfigurationProperties, AuthenticationManager authenticationManager, Clock clock) {
        return new JwtAuthenticator(jwtConfigurationProperties, authenticationManager, clock);
    }

}
