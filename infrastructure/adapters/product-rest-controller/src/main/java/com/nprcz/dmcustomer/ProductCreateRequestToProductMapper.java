package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductMapperInterface;
import com.nprcz.dmcustomer.product.ProductDTO;
import org.springframework.stereotype.Service;
@Service
 class ProductCreateRequestToProductMapper implements ProductMapperInterface<ProductCreateRequest> {

    @Override
    public ProductDTO apply(ProductCreateRequest productCreateRequest) {
        return ProductDTO.builder()
                .productSKUId(productCreateRequest.productSKUId)
                .productName(productCreateRequest.productName)
                .productPrice(productCreateRequest.productPrice)
                .productDescription(productCreateRequest.productDescription)
                .categories(productCreateRequest.categories)
                .productQuantity(productCreateRequest.productQuantity)
                .build();
    }

    @Override
    public ProductDTO mapToProductDTOFrom(ProductCreateRequest productCreateRequest) {
        return apply(productCreateRequest); }

    @Override
    public ProductCreateRequest fromProductDTO(ProductDTO productDTO) {
        return new ProductCreateRequest()
                .toBuilder()
                .productName(productDTO.productName())
                .productPrice(productDTO.productPrice())
                .productSKUId(productDTO.productSKUId())
                .productDescription(productDTO.productDescription())
                .categories(productDTO.categories())
                .productQuantity(productDTO.productQuantity())
                .build();
    }
}