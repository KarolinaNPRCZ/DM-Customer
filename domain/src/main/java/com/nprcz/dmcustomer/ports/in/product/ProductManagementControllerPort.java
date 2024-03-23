package com.nprcz.dmcustomer.ports.in.product;

public interface ProductManagementControllerPort<RP, RQ> {

    RP createProduct(RQ rq);

    RP getProductBySKU(Integer SKU);

    RP getAllProducts();

    RP getProductsByName(String name);
}
