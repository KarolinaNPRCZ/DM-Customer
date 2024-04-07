package com.nprcz.dmcustomer.productManagementFacadeConfig;


import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import com.nprcz.dmcustomer.ports.out.product.ProductService;
import com.nprcz.dmcustomer.product.ProductManagementFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductManagementFacadeConfig {

    @Bean
    ProductService productControllerPort(ProductDAOPort productDAO) {
        return new ProductManagementFacade(productDAO);
    }


}
