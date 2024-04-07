package com.nprcz.dmcustomer.product;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
public record ProductDTO(String id,
                         Integer productSKUId,
                         String productName,
                         Double productPrice,
                         String productDescription,
                         List<String> categories,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt,
                         Integer productQuantity) implements Serializable {
}
