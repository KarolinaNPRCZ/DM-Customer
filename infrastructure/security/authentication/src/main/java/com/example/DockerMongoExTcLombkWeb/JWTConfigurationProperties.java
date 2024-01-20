package com.example.DockerMongoExTcLombkWeb;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.security.jwt")
record JWTConfigurationProperties(
        String secret,
        long expirationTimeInMillis,
        String issuer
) {
}
