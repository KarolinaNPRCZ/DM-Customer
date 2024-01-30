package com.example.DockerMongoExTcLombkWeb;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class JwtAuthenticatorTest {

    private static JwtAuthenticator jwtAuthenticator;
    private static final Instant instant = Instant.now();

    @BeforeAll
    static void setUp() {
        Clock clock = Mockito.mock(Clock.class);
        when(clock.instant()).thenReturn(instant);
        AuthenticationManager authenticationManager = new AuthenticationManagerTestMock();
        JWTConfigurationProperties jwtConfigurationProperties = new JWTConfigurationProperties(
                "testSecret",
                1000 * 60 * 60,
                "testIssuer"
        );
        jwtAuthenticator = new JwtAuthenticator(jwtConfigurationProperties, authenticationManager, clock);
    }

    @Test
    void should_successfully_return_token() {
        // GIVEN
        LoginRequest loginRequestDTO = new LoginRequest(
                "testUsername",
                "testPassword"
        );

        // WHEN
        LoginResponse loginResponse = jwtAuthenticator.authenticate(loginRequestDTO);
        DecodedJWT decodedJWT = JWT.decode(loginResponse.accessToken());

        // THEN
        assertAll(() -> {
            assertThat(loginResponse.userEmail())
                    .isEqualTo(loginRequestDTO.userEmail());
            assertThat(decodedJWT.getSubject())
                    .isEqualTo(loginRequestDTO.userEmail());
            assertThat(decodedJWT.getIssuer())
                    .isEqualTo("testIssuer");
            assertThat(decodedJWT.getExpiresAt())
                    .isEqualTo(instant.plus(1, ChronoUnit.HOURS)
                            .truncatedTo(ChronoUnit.SECONDS)
                    );
        });
    }

    @Test
    void should_throw_AuthenticationException() {
        // GIVEN
        LoginRequest loginRequestDTO = new LoginRequest(
                "wrongUsername",
                "testPassword"
        );

        // WHEN && THEN
        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> jwtAuthenticator.authenticate(loginRequestDTO)
        );
        assertThat(exception.getMessage())
                .isEqualTo(
                        String.format(
                                "User with e-mail: %s not found",
                                loginRequestDTO.userEmail()
                        )
                );
    }

    @Test
    void should_throw_BadCredentialsException() {

        // GIVEN
        LoginRequest loginRequestDTO = new LoginRequest(
                "testUsername",
                "wrongPassword"
        );

        // WHEN && THEN
        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> jwtAuthenticator.authenticate(loginRequestDTO)
        );
        assertThat(exception.getMessage())
                .isEqualTo("Wrong e-mail or Password for: " + loginRequestDTO.userEmail());
    }
}