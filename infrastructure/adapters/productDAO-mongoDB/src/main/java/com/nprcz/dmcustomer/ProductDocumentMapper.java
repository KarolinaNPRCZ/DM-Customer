package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductDTO;
import com.nprcz.dmcustomer.product.ProductMapperInterface;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;
import java.util.function.Function;


@Log4j2
final class ProductDocumentMapper implements Function<ProductDocument, ProductDTO>, ProductMapperInterface<ProductDocument> {

    @Override
    public ProductDocument mapToProductDocumentFromProductDTO(ProductDTO productDTO) {
        String uniqueID = productDTO.id();
        if (uniqueID == null) {
            uniqueID = UUID.randomUUID().toString();
        }
        return ProductDocument.builder()
                .id(uniqueID)
                .productSKUId(productDTO.productSKUId())
                .productName(productDTO.productName())
                .productDescription(productDTO.productDescription())
                .productPrice(productDTO.productPrice())
                .categories(productDTO.categories())
                .createdAt(productDTO.createdAt())
                .updatedAt(productDTO.updatedAt())
                .build();


    }

    @Override
    public ProductDTO apply(ProductDocument productDocument) {
        return ProductDTO.builder()
                .id(productDocument.id)
                .productSKUId(productDocument.productSKUId)
                .productName(productDocument.productName)
                .productPrice(productDocument.productPrice)
                .productDescription(productDocument.productDescription)
                .categories(productDocument.categories)
                .createdAt(productDocument.createdAt)
                .updatedAt(productDocument.updatedAt)
                .build();
    }


}
