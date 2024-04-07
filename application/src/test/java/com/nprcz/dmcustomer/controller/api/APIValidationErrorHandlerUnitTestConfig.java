package com.nprcz.dmcustomer.controller.api;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
abstract class APIValidationErrorHandlerUnitTestConfig {

    @Bean
    APIValidationErrorHandler apiValidationErrorHandler(MessageSource messageSource) {
        return new APIValidationErrorHandler(messageSource);
    }

    @Bean
    ControllerTest controllerTest() {
        return new ControllerTest();
    }

}