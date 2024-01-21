package com.example.DockerMongoExTcLombkWeb;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

 class UserDetailsServiceMock implements UserDetailsService {


     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("username","Password10", Collections.singleton(new SimpleGrantedAuthority("USER")));
    }
}
