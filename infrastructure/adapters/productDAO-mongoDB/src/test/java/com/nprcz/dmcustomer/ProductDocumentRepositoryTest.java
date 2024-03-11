package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.utils.productutils.SamplesProductsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ContextConfiguration(classes = ProductDocumentRepositoryTestConfig.class)
class ProductDocumentRepositoryTest implements SamplesProductsResponse {
    @Autowired
    private ProductDocumentRepository productDocumentRepository;
    private ProductDocument productDocument;

    @BeforeEach
    void setUp() {
        productDocument = ProductDocument.builder()
                .productSKUId(1)
                .productName("paper")
                .productPrice(5.43)
                .productDescription("productDescriptionTest")
                .categories(List.of("testCategories", "Test"))
                .createdAt(LocalDateTime.of(2024,3,11,12,10))
                .updatedAt(LocalDateTime.of(2024,3,11,12,10).plusHours(24)).build();

    }

    @Test
    void should_successfully_save_product_and_find_by_productSKUId() {
        //GIVEN && WHEN
        productDocumentRepository.save(productDocument);
        Optional<ProductDocument> optionalProductDocument = productDocumentRepository.getProductDocumentByProductSKUId(productDocument.productSKUId);
        //THEN
        assertThat(optionalProductDocument).isPresent();
        assertThat(optionalProductDocument.get()).isEqualTo(productDocument);


    }

}