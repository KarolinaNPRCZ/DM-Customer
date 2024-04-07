package com.nprcz.dmcustomer.ports.in.product;

import com.nprcz.dmcustomer.product.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductDAOPort {
    String save(ProductDTO productDTO);

    void deleteProduct(ProductDTO productDTO);

    Optional<ProductDTO> findProductBySKUId(Integer SKUId);

    List<ProductDTO> findProductsByName(String productName);

    List<ProductDTO> getAllProducts();

    Optional<ProductDTO> updateProductQuantityBySKUId(Integer productSKUId, Integer newQuantity);


}
