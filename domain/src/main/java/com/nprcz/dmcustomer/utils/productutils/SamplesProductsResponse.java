package com.nprcz.dmcustomer.utils.productutils;

public interface SamplesProductsResponse {
    default String oneProductDocument() {
        return """
                {
                    "productSKUId": 1,
                    "productName": "papper",
                    "productPrice": 14.10,
                    "productDescription": "best papper on the world",
                    "categories": ["home", "office"],
                    "createdAt": "2024-03-09T12:34:56.789",
                    "updatedAt": "2024-03-09T12:34:56.789"
                }
                """.trim();
    }

}
