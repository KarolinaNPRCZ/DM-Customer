package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import com.nprcz.dmcustomer.product.ProductMapperInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories
class ProductDAOConfig {


    @Bean
    ProductDAOPort productDAOImpl(ProductDocumentRepository productDocumentRepository,
                                  ProductMapperInterface<ProductDocument> productDocumentMapper) {
        return new ProductDAOImpl(productDocumentMapper, productDocumentRepository);
    }

    @Bean
    ProductMapperInterface<ProductDocument> productDocumentMapper() {
        return new ProductDocumentMapper();
    }

}