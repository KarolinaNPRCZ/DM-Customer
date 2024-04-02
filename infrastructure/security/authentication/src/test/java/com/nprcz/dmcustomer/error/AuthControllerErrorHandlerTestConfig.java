package com.nprcz.dmcustomer.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
abstract class AuthControllerErrorHandlerTestConfig {
    @Bean
    TestController testController() {
        return new TestController();
    }

    @Bean
    AuthControllerErrorHandler authControllerErrorHandler() {
        return new AuthControllerErrorHandler();
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}