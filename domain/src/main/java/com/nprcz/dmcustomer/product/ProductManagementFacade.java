package com.nprcz.dmcustomer.product;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import com.nprcz.dmcustomer.ports.out.product.ProductService;

import java.util.List;

public class ProductManagementFacade implements ProductService {
    private final ProductDAOPort productDAO;

    public ProductManagementFacade(ProductDAOPort productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public String createProduct(ProductDTO productDTO) {
        return productDAO.save(productDTO);
    }

    @Override
    public ProductDTO getProductBySKUId(Integer productSKUId) {
        return productDAO.findProductBySKUId(productSKUId).orElseThrow(() -> new ProductNotFoundException(productSKUId));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public List<ProductDTO> getProductsByName(String productName) {
        return productDAO.findProductsByName(productName);
    }


}
