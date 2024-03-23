package com.nprcz.dmcustomer.product;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import com.nprcz.dmcustomer.ports.out.product.ProductService;

import java.util.List;

public class ProductManagementFacade implements ProductService {
    private final ProductDAOPort productDAOPort;

    public ProductManagementFacade(ProductDAOPort productDAOPort) {
        this.productDAOPort = productDAOPort;
    }

    @Override
    public Integer createProduct(ProductDTO productDTO) {
        return productDAOPort.save(productDTO);
    }

    @Override
    public ProductDTO getProductBySKUId(Integer productSKUId) {
        return productDAOPort.findProductBySKUId(productSKUId).orElseThrow(() -> new ProductNotFoundException(productSKUId));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productDAOPort.getAllProducts();
    }

    @Override
    public List<ProductDTO> getProductsByName(String productName) {
        return productDAOPort.findProductsByName(productName);
    }


}
