package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.in.product.ProductManagementControllerPort;
import com.nprcz.dmcustomer.ports.out.product.ProductService;
import com.nprcz.dmcustomer.product.ProductDTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Log4j2
class ProductManagementController implements ProductManagementControllerPort<ResponseEntity<?>, ProductCreateRequest> {

    private final ProductService productService;
    private final ProductCreateRequestToProductMapper productCreateRequestToProductMapper;

    public ProductManagementController(ProductService productService, ProductCreateRequestToProductMapper productCreateRequestToProductMapper) {
        this.productService = productService;
        this.productCreateRequestToProductMapper = productCreateRequestToProductMapper;
    }

    @Override
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        log.info("Handle Request = ProductManagementController: createProduct method");
        String productUUID = productService.createProduct(productCreateRequestToProductMapper.mapToProductDTOFrom(productCreateRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(productUUID);
    }

    @Override
    @GetMapping("/{SKU}")
    public ResponseEntity<ProductDTO> getProductBySKU(@PathVariable Integer SKU) {
        log.info("Trying to find product with sku: {}...", SKU);
        ProductDTO productDTO = productService.getProductBySKUId(SKU);
        log.info("Product has successfully find: {}", productDTO);
        return ResponseEntity.ok(productDTO);

    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("Trying to find all products...");
        List<ProductDTO> products = productService.getAllProducts();
        log.info("User has successfully got all products");
        return ResponseEntity.ok(
                products
        );

    }

    @Override
    @GetMapping("/find/{productName}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String productName) {
        log.info("Trying to find product with name: {}...", productName);
        List<ProductDTO> productDTO = productService.getProductsByName(productName);
        log.info("Product has successfully find: {}", productDTO);

        return ResponseEntity.ok(productDTO);
    }
}
