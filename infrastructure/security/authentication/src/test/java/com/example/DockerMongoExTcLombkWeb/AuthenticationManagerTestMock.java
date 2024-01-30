package com.example.DockerMongoExTcLombkWeb;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

 class AuthenticationManagerTestMock implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            if (!authentication.getPrincipal().equals("testUsername")) {
                throw new AuthenticationException("User with e-mail: " + authentication.getPrincipal() + " not found") {};
            }
            if (!authentication.getCredentials().equals("testPassword")) {
                throw new BadCredentialsException("Wrong password");
            }
            User user = new User(
                    authentication.getPrincipal().toString(),
                    authentication.getCredentials().toString(),
                    Collections.singleton(new SimpleGrantedAuthority("ADMIN"))
            );
            return new UsernamePasswordAuthenticationToken(
                    user,
                    authentication.getCredentials(),
                    authentication.getAuthorities()
            );
        }
    }

