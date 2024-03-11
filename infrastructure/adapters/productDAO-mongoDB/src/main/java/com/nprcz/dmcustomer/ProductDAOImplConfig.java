package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories
class ProductDAOImplConfig {


    @Bean
    ProductDAOPort productDAO(ProductDocumentRepository productDocumentRepository,
            ProductDocumentMapper productDocumentMapper) {
        return new ProductDAOImpl(productDocumentMapper, productDocumentRepository);
    }


}