package com.example.DockerMongoExTcLombkWeb.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserControllerExceptionHandlerTestConfig {

    @Bean
    UserLoginAndSignUpControllerTest userLoginAndSignUpControllerTest() {
        return new UserLoginAndSignUpControllerTest();
    }

    @Bean
    UserControllerExceptionHandler userControllerExceptionHandler() {
        return new UserControllerExceptionHandler();
    }
}