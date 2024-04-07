package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import com.nprcz.dmcustomer.product.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Log4j2
class ProductDAOImpl implements ProductDAOPort {


    private final ProductMapperInterface<ProductDocument> productDocumentMapper;
    private final ProductDocumentRepository productDocumentRepository;

    public ProductDAOImpl(ProductMapperInterface<ProductDocument> productDocumentMapper, ProductDocumentRepository productDocumentRepository) {
        this.productDocumentMapper = productDocumentMapper;
        this.productDocumentRepository = productDocumentRepository;
    }

    @Override
    public String save(ProductDTO productDTO) {
        ProductDocument productDocument = productDocumentMapper.fromProductDTO(productDTO);

        LocalDateTime createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        productDocument.setCreatedAt(createdAt);

        log.info("Trying to save product to database...");

        ProductDocument savedProductDocument;
        try {
            savedProductDocument = productDocumentRepository.save(productDocument);
        } catch (DuplicateKeyException exception) {
            log.warn("Failed to save product to database");
            throw new ProductAlreadyExistsException(productDocument.getProductSKUId());
        }

        log.info("Product saved to database successfully");
        return savedProductDocument.getId();
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) {
        productDocumentRepository.deleteByProductSKUId(productDTO.productSKUId());
        log.info("Product deleted successfully");

    }

    @Override
    public Optional<ProductDTO> findProductBySKUId(Integer SKUId) {
        log.info("ProductDAO trying to find product with SKU " + SKUId);
        Optional<ProductDTO> recivedProduct = productDocumentRepository.getProductDocumentByProductSKUId(SKUId).map(productDocumentMapper);
        log.info("ProductDAO found product with SKU " + SKUId);
        return recivedProduct;
    }

    @Override
    public List<ProductDTO> findProductsByName(String productName) {

        log.info("ProductDAO trying to find products with " + productName);
        List<ProductDTO> productsDocument = productDocumentRepository
                .getProductDocumentsByProductNameContainsIgnoreCase(productName)
                .stream()
                .map(productDocumentMapper)
                .toList();
        ;
        log.info("ProductDAO found products with " + productName);
        return productsDocument;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("ProductDAO trying to find all products");
        List<ProductDTO> productDTOSList = productDocumentRepository.findAll()
                .stream()
                .map(productDocumentMapper)
                .toList();
        log.info("ProductDAO found products: " + productDTOSList.toString());
        return productDTOSList;
    }

    @Override
    public Optional<ProductDTO> updateProductQuantityBySKUId(Integer productSKUId, Integer newQuantity) {
        log.info("Trying to find product with SKU: {}", productSKUId);
        ProductDocument productDocumentBeforeUpdate = productDocumentRepository
                .getProductDocumentByProductSKUId(productSKUId)
                .orElseThrow(() -> new ProductNotFoundException(productSKUId));
        log.info("Successfully found product with SKU: {}", productSKUId);

        int updatedQuantity = productDocumentBeforeUpdate.getProductQuantity() + newQuantity;
        if (updatedQuantity < 0) {
            throw new InvalidProductQuantityException(productDocumentBeforeUpdate.productQuantity);
        }
        productDocumentBeforeUpdate.setProductQuantity(updatedQuantity);

        LocalDateTime updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        productDocumentBeforeUpdate.setUpdatedAt(updatedAt);

        log.info("Trying to save product after update");
        ProductDocument updatedProductDocument = productDocumentRepository.save(productDocumentBeforeUpdate);
        log.info("Successfully saved product after update");

        ProductDTO updatedProductDTO = productDocumentMapper.mapToProductDTOFrom(updatedProductDocument);
        return Optional.of(updatedProductDTO);
    }
}