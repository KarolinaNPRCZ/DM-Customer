package com.example.DockerMongoExTcLombkWeb;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;


@Log4j2
class JwtAuthenticator {

    private final JWTConfigurationProperties jwtConfigurationProperties;
    private final AuthenticationManager authenticationManager;
    private final Clock clock;


    public JwtAuthenticator(JWTConfigurationProperties jwtConfigurationProperties, AuthenticationManager authenticationManager, Clock clock) {
        this.jwtConfigurationProperties = jwtConfigurationProperties;
        this.authenticationManager = authenticationManager;
        this.clock = clock;
    }

    LoginResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication;
        log.info("Trying to authenticate user");
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.userEmail(),
                            loginRequest.userPassword()
                    )
            );
        } catch (BadCredentialsException badCredentialsException) {
            log.error("User has used wrong credentials to authenticate!! with email: {} password: {}",
                    loginRequest.userEmail(),
                    loginRequest.userPassword());
            throw new AuthenticationException("Wrong e-mail or Password for: " + loginRequest.userEmail()) {
            };
        }
        log.info("User authenticate success");
        User user = (User) authentication.getPrincipal();
        String token = buildToken(user);
        return LoginResponse.builder().accessToken(token).userEmail(user.getUsername()).build();


    }

    private String buildToken(User user) {
        String secretKey = jwtConfigurationProperties.secret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String issuer = jwtConfigurationProperties.issuer();
        Instant now = clock.instant();
        Instant expirationTimeInMillis = now.plusMillis(jwtConfigurationProperties.expirationTimeInMillis());

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expirationTimeInMillis))
                .withIssuer(issuer)
                .sign(algorithm);

    }
}
