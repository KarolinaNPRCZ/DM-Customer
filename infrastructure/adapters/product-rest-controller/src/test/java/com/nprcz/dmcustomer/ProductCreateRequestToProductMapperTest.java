package com.nprcz.dmcustomer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nprcz.dmcustomer.product.ProductDTO;
import com.nprcz.dmcustomer.utils.productutils.SamplesProductsResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductCreateRequestToProductMapperTest implements SamplesProductsResponse {

    private ProductCreateRequestToProductMapper productCreateRequestToProductMapper;
    private ObjectMapper objectMapper;
    private ProductDTO productDTO;

    private ProductCreateRequest productCreateRequest;

    @BeforeEach
    void setUP() throws JsonProcessingException {
        this.productCreateRequestToProductMapper = new ProductCreateRequestToProductMapper();
        this.objectMapper = new ObjectMapper();
        productDTO = oneProductDocumentDTO();
        productCreateRequest = objectMapper.readValue(oneProductDocument(), ProductCreateRequest.class);
    }


    @Test
    void should_successfully_map_productReq_by_apply_method() {
        //Given && WHEN
      ProductDTO mappedProduct =  productCreateRequestToProductMapper.apply(productCreateRequest);
       //THEN
        assertThat(mappedProduct).usingRecursiveComparison().ignoringFields("id","createdAt","updatedAt").isEqualTo(productDTO);

    }

    @Test
    void should_successfully_map_productReq_by_mapToProductDTOFrom() {
        //Given && WHEN
        ProductDTO mappedProduct =  productCreateRequestToProductMapper.mapToProductDTOFrom(productCreateRequest);
        //THEN
        assertThat(mappedProduct).usingRecursiveComparison().ignoringFields("id","createdAt","updatedAt").isEqualTo(productDTO);


    }

    @Test
    void should_successfully_map_productDTO_by_fromProductDTO() {
        //Given && WHEN
        ProductCreateRequest mappedProductReq =  productCreateRequestToProductMapper.fromProductDTO(productDTO);
        //THEN
        assertThat(mappedProductReq).usingRecursiveComparison().ignoringFields("id","createdAt","updatedAt").isEqualTo(productCreateRequest);


    }
}