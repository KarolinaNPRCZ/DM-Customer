package com.nprcz.dmcustomer;


import lombok.*;
import org.springframework.data.annotation.Id;
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
     Integer productSKUId;
     String productName;
     Double productPrice;
     String productDescription;
     List<String> categories;
     LocalDateTime createdAt;
     LocalDateTime updatedAt;


}
