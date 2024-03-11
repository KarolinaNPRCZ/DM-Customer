package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductDTO;

class ProductDocumentMapper {
    public ProductDocument mapToProductDocumentFromProductDTO(ProductDTO productDTO) {
    return ProductDocument.builder()
            .productSKUId(productDTO.productSKUId())
            .productName(productDTO.productName())
            .productPrice(productDTO.productPrice())
            .productDescription(productDTO.productDescription())
            .categories(productDTO.categories())
            .createdAt(productDTO.createdAt())
            .updatedAt(productDTO.updatedAt())
            .build();
    }
}
