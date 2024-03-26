package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductDTO;
import com.nprcz.dmcustomer.utils.productutils.SamplesProductsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDocumentMapperTest implements SamplesProductsResponse {

    private ProductDocumentMapper productDocumentMapper;
    private ProductDocument productDocument;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        this.productDocumentMapper = new ProductDocumentMapper();
        productDocument = ProductDocument.builder()
                .id("testID")
                .productSKUId(1)
                .productName("Premium A4 Copy Paper")
                .productPrice(14.10)
                .productDescription("High-quality A4 copy paper suitable for home and office use.")
                .categories(List.of("office", "stationery"))
                .createdAt(LocalDateTime.of(2024, 3, 11, 12, 10))
                .updatedAt(LocalDateTime.of(2024, 3, 11, 12, 10).plusHours(24)).build();
        productDTO = oneProductDocumentDTO();
        productDTO.toBuilder().id("testID").build();
    }

    @Test
    void should_successfully_map_To_ProductDocument_From_ProductDTO() {
        //Given && WHEN
        ProductDocument mappedProductDocument = productDocumentMapper.fromProductDTO(productDTO);
        //THEN
        assertThat(mappedProductDocument).usingRecursiveComparison().ignoringFields("id").isEqualTo(productDocument);
    }
    @Test
    void should_successfully_map_To_ProductDTO_From_ProductDocument() {
        //Given && WHEN
        ProductDTO mappedProductDTO = productDocumentMapper.apply(productDocument);
        //THEN
        assertThat(mappedProductDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(productDTO);
    }
}