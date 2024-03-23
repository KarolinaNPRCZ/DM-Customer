package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductMapperInterface;
import com.nprcz.dmcustomer.product.ProductDTO;
import org.springframework.stereotype.Service;

@Service
 class ProductCreateRequestToProductMapper implements ProductMapperInterface<ProductCreateRequest> {


    @Override
    public ProductDTO mapFrom(ProductCreateRequest productCreateRequest) {
        return apply(productCreateRequest);
    }

    @Override
    public ProductDTO apply(ProductCreateRequest productCreateRequest) {
        return ProductDTO.builder()
                .productSKUId(productCreateRequest.productSKUId)
                .productName(productCreateRequest.productName)
                .productPrice(productCreateRequest.productPrice)
                .productDescription(productCreateRequest.productDescription)
                .categories(productCreateRequest.categories)
                .build();
    }
}