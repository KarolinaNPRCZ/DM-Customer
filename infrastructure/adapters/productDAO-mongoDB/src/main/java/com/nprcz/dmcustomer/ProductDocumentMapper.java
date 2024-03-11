package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.product.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Log4j2
@Service
class ProductDocumentMapper implements Function<ProductDTO, ProductDocument> {

    @Override
    public ProductDocument apply(ProductDTO productDTO) {
        return new ProductDocument(
                productDTO.productSKUId(),
                productDTO.productName(),
                productDTO.productPrice(),
                productDTO.productDescription(),
                productDTO.categories(),
                productDTO.createdAt(),
                productDTO.updatedAt());

    }

    ProductDocument mapToProductDocumentFromProductDTO(ProductDTO productDTO) {
        return apply(productDTO);
    }
}
