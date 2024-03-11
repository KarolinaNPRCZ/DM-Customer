package com.nprcz.dmcustomer.ports.in.product;

import com.nprcz.dmcustomer.product.ProductDTO;

public interface ProductDAOPort {
    Integer save(ProductDTO productDTO);
}
