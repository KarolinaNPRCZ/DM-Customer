package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import com.nprcz.dmcustomer.product.ProductDTO;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
class ProductDAOImpl implements ProductDAOPort {


    private final ProductDocumentMapper productDocumentMapper;
    private final ProductDocumentRepository productDocumentRepository;

    public ProductDAOImpl(ProductDocumentMapper productDocumentMapper, ProductDocumentRepository productDocumentRepository) {
        this.productDocumentMapper = productDocumentMapper;
        this.productDocumentRepository = productDocumentRepository;
    }
    @Override
    public Integer save(ProductDTO productDTO) {
        ProductDocument productDocument = productDocumentMapper.mapToProductDocumentFromProductDTO(productDTO);
        log.info("productDocumentDAO try save product to database");
        ProductDocument savedProductDocument = productDocumentRepository.save(productDocument);
        return savedProductDocument.productSKUId;
    }

    @Override
    public Optional<ProductDTO> findProductBySKUId(Integer SKUId) {
        return Optional.empty();
    }

    @Override
    public List<ProductDTO> findProductsByName(String productName) {
       // List<ProductDocument> productsDocument = productDocumentRepository.getProductDocumentsByProductNameContainsIgnoreCase(productName);

        return List.of();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return null;
    }
}