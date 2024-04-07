package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.product.InvalidProductQuantityException;
import com.nprcz.dmcustomer.product.ProductAlreadyExistsException;
import com.nprcz.dmcustomer.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductManagementControllerExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    ProductExceptionsResponse handle(ProductAlreadyExistsException exception) {

        return new ProductExceptionsResponse(
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ProductExceptionsResponse handle(ProductNotFoundException exception) {

        return new ProductExceptionsResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ProductExceptionsResponse handle(InvalidProductQuantityException exception) {

        return new ProductExceptionsResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

}
