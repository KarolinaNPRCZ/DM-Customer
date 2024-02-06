package com.nprcz.dmcustomer.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
class APIValidationErrorHandlerUnitTestConfig {

    @Bean
    APIValidationErrorHandler apiValidationErrorHandler(MessageSource messageSource) {
        return new APIValidationErrorHandler(messageSource);
    }

    @Bean
    ControllerTest testController() {
        return new ControllerTest();
    }

}