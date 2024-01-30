package com.example.DockerMongoExTcLombkWeb;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    private MockHttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userDetailsService = new UserDetailsServiceMock();
        request = new MockHttpServletRequest();
        filterChain = Mockito.spy(new MockFilterChain());
        response = new MockHttpServletResponse();
        JWTConfigurationProperties jwtConfigurationProperties = new JWTConfigurationProperties(
                "testSecret",
                1000 * 60 * 60,
                "testIssuer"
        );
         jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtConfigurationProperties,userDetailsService);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.getContext()
                .setAuthentication(null);
    }

    @Test
    void should_invoke_filter_method_with_no_authentication_header() throws ServletException, IOException {
        // GIVEN && WHEN
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // THEN
        verify(filterChain, times(1))
                .doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void should_authorize_and_return_JWT_token() throws ServletException, IOException {
        // GIVEN
        request.addHeader("Authorization", "Bearer testToken");
        JWTVerifier jwtVerifier = mock(JWTVerifier.class);
        DecodedJWT decodedJWTMock = mock(DecodedJWT.class);

        // WHEN
        when(jwtVerifier.verify("testToken")).thenReturn(decodedJWTMock);
        ReflectionTestUtils.setField(jwtAuthenticationFilter, "jwtVerifier", jwtVerifier);
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // THEN
        verify(filterChain).doFilter(request, response);
        assertEquals(
                decodedJWTMock.getSubject(),
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal()
        );
        assertNull(
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getCredentials()
        );
    }
}