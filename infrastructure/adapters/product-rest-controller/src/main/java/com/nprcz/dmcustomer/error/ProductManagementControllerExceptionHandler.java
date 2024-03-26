package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.product.ProductAlreadyExistsException;
import com.nprcz.dmcustomer.product.ProductNotFoundException;
import com.nprcz.dmcustomer.user.UserAlreadyExistsException;
import com.nprcz.dmcustomer.user.UserNotFoundException;
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
    ProductAlreadyExistsResponse handle(ProductAlreadyExistsException exception) {

        return new ProductAlreadyExistsResponse(
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ProductAlreadyExistsResponse handle(ProductNotFoundException exception) {

        return new ProductAlreadyExistsResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

}
