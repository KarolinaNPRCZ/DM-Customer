package com.nprcz.dmcustomer.product;

import java.io.Serializable;
import java.util.function.Function;

public interface ProductMapperInterface<T> extends Function<T,ProductDTO>, Serializable {
    default ProductDTO toProductDTO(T t){
        return apply(t);
    }
    ProductDTO mapFrom(T t);
}
