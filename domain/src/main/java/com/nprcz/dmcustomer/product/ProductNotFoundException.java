package com.nprcz.dmcustomer.product;

public class ProductNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Product with SKU: %d not found";
    public ProductNotFoundException(Integer productSKUId) {
        super(MESSAGE.formatted(productSKUId));
    }
}
