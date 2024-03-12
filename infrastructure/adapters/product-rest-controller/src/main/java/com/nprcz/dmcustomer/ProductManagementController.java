package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.in.product.ProductManagementControllerPort;
import com.nprcz.dmcustomer.ports.out.product.ProductService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Log4j2
class ProductManagementController implements ProductManagementControllerPort<ResponseEntity<?>,ProductCreateRequest> {

    private final ProductService productService;
    private final ProductCreateRequestToProductMapper productCreateRequestToProductMapper;

    public ProductManagementController(ProductService productService, ProductCreateRequestToProductMapper productCreateRequestToProductMapper) {
        this.productService = productService;
        this.productCreateRequestToProductMapper = productCreateRequestToProductMapper;
    }
    @Override
    @PostMapping("/create")
    public ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        log.info("Handle Request = ProductManagementController");
        Integer register = productService.createProduct(productCreateRequestToProductMapper.toProductDTO(productCreateRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @Override
    public ResponseEntity<?> getProductBySKU(Integer SKU) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        return null;
    }

    @Override
    public ResponseEntity<?> getProductByName(String name) {
        return null;
    }
}
