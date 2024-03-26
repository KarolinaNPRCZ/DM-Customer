package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import com.nprcz.dmcustomer.product.ProductAlreadyExistsException;
import com.nprcz.dmcustomer.product.ProductDTO;
import com.nprcz.dmcustomer.product.ProductMapperInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;

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
    public Integer save(ProductDTO productDTO) {
        ProductDocument productDocument = productDocumentMapper.mapToProductDocumentFromProductDTO(productDTO);
        log.info("productDocumentDAO try save product to database");
        ProductDocument savedProductDocument;
        try {
            savedProductDocument = productDocumentRepository.save(productDocument);
        } catch (
                DuplicateKeyException exception) {
            log.warn("productDocumentDAO filed save product to database");
            throw new ProductAlreadyExistsException(productDocument.productSKUId);
        }
        log.info("productDocumentDAO save product to database");
        return savedProductDocument.productSKUId;
    }

    @Override
    public Integer deleteProduct(ProductDTO productDTO) {
        ProductDocument productDocument = productDocumentMapper.mapToProductDocumentFromProductDTO(productDTO);
        productDocumentRepository.deleteByProductSKUId(productDocument.productSKUId);
        log.info("Product deleted successfully");
        return productDocument.productSKUId;
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
                 .toList();;
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
}