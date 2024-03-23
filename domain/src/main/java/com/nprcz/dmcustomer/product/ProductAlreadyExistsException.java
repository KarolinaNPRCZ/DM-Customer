package com.nprcz.dmcustomer.product;

public class ProductAlreadyExistsException extends RuntimeException {
    public static final String MESSAGE = "Product with SKU: %d already exist";
    public ProductAlreadyExistsException(Integer productSKUId) {
        super(MESSAGE.formatted(productSKUId));
    }
}
