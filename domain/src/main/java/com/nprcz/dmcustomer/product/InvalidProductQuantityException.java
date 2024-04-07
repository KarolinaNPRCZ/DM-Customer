package com.nprcz.dmcustomer.product;

public class InvalidProductQuantityException extends RuntimeException {

    public static final String MESSAGE = "Product quantity after update cannot be less than 0. Actual product quantity: %d";
    public InvalidProductQuantityException(Integer quantity) {
        super(MESSAGE.formatted(quantity));
    }


}
