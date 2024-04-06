package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.product.ProductAlreadyExistsException;
import com.nprcz.dmcustomer.product.ProductNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductManagementControllerTest {


    @PostMapping("/test/003")
    ProductAlreadyExistsResponse productAlreadyExistsResponseTest() {
        throw new ProductAlreadyExistsException(2);
    }

    @PostMapping("/test/004")
    ProductAlreadyExistsResponse productAlreadyExistsResponseTest_2() {
        throw new ProductNotFoundException(2);
    }
}
