package com.nprcz.dmcustomer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Builder(toBuilder = true)
@Log4j2
@Getter
@AllArgsConstructor
@NoArgsConstructor
class ProductCreateRequest {

    @NotNull(message = "{not.null}")
    @JsonProperty("sku")
    @PositiveOrZero(message = "{validation.positive.or.zero}")
    Integer productSKUId;

    @NotNull(message = "{not.null}")
    @NotBlank(message = "{not.blank}")
    @JsonProperty("name")
    String productName;

    @NotNull(message = "{not.null}")
    @JsonProperty("price")
    @DecimalMin(value = "0.00",message = "{validation.decimal.min}")
    @DecimalMax(value = "10000.00",message = "{validation.decimal.max}")
    Double productPrice;

    @NotNull(message = "{not.null}")
    @JsonProperty("description")
    String productDescription;

    @NotNull(message = "{not.null}")
    @JsonProperty("categories")
    List<String> categories;

}
