package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.product.InvalidProductQuantityException;
import com.nprcz.dmcustomer.product.ProductAlreadyExistsException;
import com.nprcz.dmcustomer.product.ProductNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductManagementControllerTest {


    @PostMapping("/test/003")
    ProductExceptionsResponse productAlreadyExistsResponseTest() {
        throw new ProductAlreadyExistsException(2);
    }

    @PostMapping("/test/004")
    ProductExceptionsResponse productAlreadyExistsResponseTest_2() {
        throw new ProductNotFoundException(2);
    }

    @PostMapping("/test/005")
    ProductExceptionsResponse productAlreadyExistsResponseTest_3() {
        throw new InvalidProductQuantityException(2);
    }

}
