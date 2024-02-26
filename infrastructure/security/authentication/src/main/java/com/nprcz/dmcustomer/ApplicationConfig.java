package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.out.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class ApplicationConfig {
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(UserDetailsService userDetailsService, JWTConfigurationProperties jwtConfigurationProperties) {
        return new JwtAuthenticationFilter(jwtConfigurationProperties, userDetailsService);

    }

    @Bean
    UserDetailsService userDetailsService(UserService userService, UserMapperInterfaceImpl userMapperInterfaceImpl) {
        return username -> userMapperInterfaceImpl.fromUserDTO(
                userService.getUserDTOByUserEmail(username)
        );
    }


    @Bean
    AuthenticationProvider authenticationProvider(UserService userService, UserMapperInterfaceImpl userMapperInterfaceImpl) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService(userService, userMapperInterfaceImpl));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    UserMapperInterfaceImpl userMapperInterface() {
        return new UserMapperInterfaceImpl();
    }


}

