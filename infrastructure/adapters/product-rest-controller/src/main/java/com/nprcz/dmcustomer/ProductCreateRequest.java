package com.nprcz.dmcustomer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "{not.blank}")
    @JsonProperty("sku")
    Integer productSKUId;

    @NotNull(message = "{not.null}")
    @NotBlank(message = "{not.blank}")
    @JsonProperty("name")
    String productName;

    @NotNull(message = "{not.null}")
    @NotBlank(message = "{not.blank}")
    @JsonProperty("price")
    Double productPrice;

    @NotNull(message = "{not.null}")
    @JsonProperty("description")
    String productDescription;

    @NotNull(message = "{not.null}")
    @JsonProperty("categories")
    List<String> categories;

}
