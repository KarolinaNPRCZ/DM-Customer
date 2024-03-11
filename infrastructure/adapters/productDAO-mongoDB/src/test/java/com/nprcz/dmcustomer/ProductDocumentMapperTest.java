package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDocumentMapperTest {

    private ProductDocumentMapper productDocumentMapper;
    private  ProductDocument productDocument;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp(){
        this.productDocumentMapper = new ProductDocumentMapper();
        List<String> testCategories = new ArrayList<>();
        testCategories.add("home");
        testCategories.add("office");
        String productNameTest = "papper";
        double productPriceTest = 14.10;
        String productDescriptionTest = "best papper on the world";
        LocalDateTime productCreatedAtTest = LocalDateTime.of(2024,3,3,14,30);
        LocalDateTime productUpdatedAtTest = productCreatedAtTest.plusHours(24);
        productDocument = ProductDocument.builder()
                .productSKUId(1)
                .productName(productNameTest)
                .productPrice(productPriceTest)
                .productDescription(productDescriptionTest)
                .categories(testCategories)
                .createdAt(productCreatedAtTest)
                .updatedAt(productUpdatedAtTest).build();
         productDTO = ProductDTO.builder()
                .productSKUId(1)
                .productName(productNameTest)
                .productPrice(productPriceTest)
                .productDescription(productDescriptionTest)
                .categories(testCategories)
                .createdAt(productCreatedAtTest)
                .updatedAt(productUpdatedAtTest).build();
    }

    @Test
    void should_successfully_map_To_ProductDocument_From_ProductDTO() {
        //Given && WHEN
        ProductDocument mappedProductDocument = productDocumentMapper.mapToProductDocumentFromProductDTO(productDTO);
        //THEN
        assertThat(mappedProductDocument).usingRecursiveComparison().isEqualTo(productDocument);
    }
}