package com.nprcz.dmcustomer.product;

import java.io.Serializable;
import java.util.function.Function;

public interface ProductMapperInterface<T> extends Function<T,ProductDTO>, Serializable {
    default ProductDTO mapToProductDTOFrom(T t){
        return apply(t);
    }
    T mapToProductDocumentFromProductDTO(ProductDTO productDTO);
}
