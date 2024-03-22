package com.nprcz.dmcustomer.ports.out.product;

import com.nprcz.dmcustomer.product.ProductDTO;

import java.util.List;

public interface ProductService {
    Integer createProduct(ProductDTO productDTO);

    ProductDTO getProductBySKUId(Integer ProductSKUId);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductByName(String productName);
}
