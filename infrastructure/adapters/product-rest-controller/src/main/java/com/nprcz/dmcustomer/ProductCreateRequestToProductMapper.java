package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductMapperInterface;
import com.nprcz.dmcustomer.product.ProductDTO;
import org.springframework.stereotype.Service;
//TODO ProductMapperInterface
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
                .build();
    }

    @Override
    public ProductDTO mapToProductDTOFrom(ProductCreateRequest productCreateRequest) {
        return apply(productCreateRequest); }

    @Override
    public ProductCreateRequest mapToProductDocumentFromProductDTO(ProductDTO productDTO) {
        return null;
    }
}