package com.nprcz.dmcustomer.ports.in.product;

import com.nprcz.dmcustomer.product.ProductDTO;

public interface ProductManagementControllerPort<RP, RQ> {

    RP createProduct(RQ rq);

    RP getProductBySKU(Integer SKU);

    RP getAllProducts();

    RP getProductsByName(String name);

    RP updateProductQuantityBySKUId(Integer sku, Integer quantity);
}
