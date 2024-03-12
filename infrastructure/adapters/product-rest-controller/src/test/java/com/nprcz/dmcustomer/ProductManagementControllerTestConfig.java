package com.nprcz.dmcustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nprcz.dmcustomer.ports.out.product.ProductService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
abstract class ProductManagementControllerTestConfig {

    @Bean
    ProductService productService() {

        return Mockito.mock(ProductService.class);
    }

    @Bean
    ProductManagementController productManagementController(ProductService productService, ProductCreateRequestToProductMapper productCreateRequestToProductMapper) {
        return new ProductManagementController(productService, productCreateRequestToProductMapper);
    }
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    ProductCreateRequestToProductMapper productCreateRequestToProductMapper(){
        return new ProductCreateRequestToProductMapper();
    }
}
