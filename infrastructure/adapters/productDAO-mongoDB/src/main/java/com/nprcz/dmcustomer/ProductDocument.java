package com.nprcz.dmcustomer;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
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
