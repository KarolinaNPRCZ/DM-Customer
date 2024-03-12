package com.nprcz.dmcustomer.ports.out.product;

import com.nprcz.dmcustomer.product.ProductDTO;

public interface ProductService {
    Integer createProduct(ProductDTO productDTO);
}
