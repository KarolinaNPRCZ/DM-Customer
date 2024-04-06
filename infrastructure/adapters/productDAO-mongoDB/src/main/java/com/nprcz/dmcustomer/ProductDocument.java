package com.nprcz.dmcustomer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@Builder(toBuilder = true)

class ProductDocument {

    @Id
    String id;
    @Indexed(unique = true)
    Integer productSKUId;

    String productName;

    Double productPrice;

    String productDescription;

    List<String> categories;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    Integer productQuantity;
}
