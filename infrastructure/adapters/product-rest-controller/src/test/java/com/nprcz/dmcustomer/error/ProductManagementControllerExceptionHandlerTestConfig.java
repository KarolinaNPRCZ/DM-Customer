package com.nprcz.dmcustomer.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductManagementControllerExceptionHandlerTestConfig {

    @Bean
    ProductManagementControllerTest productManagementControllerTest() {
        return new ProductManagementControllerTest();
    }

    @Bean
    ProductManagementControllerExceptionHandler productManagementControllerExceptionHandler() {
        return new ProductManagementControllerExceptionHandler();
    }
}