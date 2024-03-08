package com.nprcz.dmcustomer.product;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
public record ProductDTO(Integer productId,
                         String productName,
                         double productPrice,
                         String productDescription,
                         List<String> categories,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
}
