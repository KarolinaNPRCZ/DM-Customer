package com.nprcz.dmcustomer.ports.in.product;

import com.nprcz.dmcustomer.product.ProductDTO;
import com.nprcz.dmcustomer.user.DTO.UserDTO;

import java.io.Serializable;
import java.util.function.Function;

public interface ProductMapperInterface<T> extends Function<T, ProductDTO>, Serializable {
    default ProductDTO toProductDTO(T t){
        return apply(t);
    }
    T fromProductDTO(ProductDTO productDTO);
}
