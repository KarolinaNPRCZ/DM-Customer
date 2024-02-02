package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.out.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
abstract class UserLoginAndSignUpControllerTestConfig {
    @Bean
    UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserLoginAndSignUpController userLoginAndSignUpController(UserService userService, PasswordEncoder passwordEncoder) {
        return new UserLoginAndSignUpController(userService, passwordEncoder);
    }
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }


}
