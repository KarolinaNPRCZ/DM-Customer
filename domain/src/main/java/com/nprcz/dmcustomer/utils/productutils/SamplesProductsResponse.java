package com.nprcz.dmcustomer.utils.productutils;

import com.nprcz.dmcustomer.product.ProductDTO;

import javax.swing.event.ListDataEvent;
import java.time.LocalDateTime;
import java.util.List;

public interface SamplesProductsResponse {
    default String oneProductDocument() {
        return """
                {
                    "productSKUId": 1,
                    "productName": "Premium A4 Copy Paper",
                    "productPrice": 14.10,
                    "productDescription": "High-quality A4 copy paper suitable for home and office use.",
                    "categories": ["office", "stationery"],
                    "createdAt": "2024-03-09T12:34:56.789",
                    "updatedAt": "2024-03-09T12:34:56.789"
                }
                """.trim();
    }

    default ProductDTO oneProductDocumentDTO() {
        return ProductDTO.builder()
                .productSKUId(1)
                .productName("Premium A4 Copy Paper")
                .productPrice(14.10)
                .productDescription("High-quality A4 copy paper suitable for home and office use.")
                .categories(List.of("office", "stationery"))
                .createdAt(LocalDateTime.of(2024,3,11,12,10))
                .updatedAt(LocalDateTime.of(2024,3,11,12,10).plusHours(24)).build();

    }
    default ProductDTO twoProductDocumentDTO() {
        return ProductDTO.builder()
                .productSKUId(2)
                .productName("Premium A4 Copy Paper")
                .productPrice(14.10)
                .productDescription("High-quality A4 copy paper suitable for home and office use.")
                .categories(List.of("office", "stationery"))
                .createdAt(LocalDateTime.of(2024,3,11,12,10))
                .updatedAt(LocalDateTime.of(2024,3,11,12,10).plusHours(24)).build();

    }
    default List<ProductDTO> threeProductDocumentDTOs() {
        return List.of(ProductDTO.builder()
                .productSKUId(3)
                .productName("Premium A4 Copy Paper")
                .productPrice(14.10)
                .productDescription("High-quality A4 copy paper suitable for home and office use.")
                .categories(List.of("office", "stationery"))
                .createdAt(LocalDateTime.of(2024,3,11,12,10))
                .updatedAt(LocalDateTime.of(2024,3,11,12,10).plusHours(24)).build(),
                ProductDTO.builder()
                .productSKUId(4)
                .productName("ECO A4 Copy Paper")
                .productPrice(16.10)
                .productDescription("High-quality A4 ECO copy paper suitable for home and office use.")
                .categories(List.of("office", "stationery"))
                .createdAt(LocalDateTime.of(2024,3,11,12,10))
                .updatedAt(LocalDateTime.of(2024,3,11,12,10).plusHours(24)).build(),
                ProductDTO.builder()
                        .productSKUId(5)
                        .productName("ECO A3 Paper")
                        .productPrice(21.10)
                        .productDescription("High-quality A3 paper suitable for home and office use.")
                        .categories(List.of("office", "stationery"))
                        .createdAt(LocalDateTime.of(2024,3,11,12,10))
                        .updatedAt(LocalDateTime.of(2024,3,11,12,10).plusHours(24)).build());

    }

}
