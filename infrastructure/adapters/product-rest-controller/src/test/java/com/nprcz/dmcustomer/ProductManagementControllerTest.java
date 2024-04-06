package com.nprcz.dmcustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nprcz.dmcustomer.ports.out.product.ProductService;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProductManagementController.class)
@ContextConfiguration(classes = ProductManagementControllerTestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;


    @Test
    void should_successfully_createProduct_by_invoking_create_product_method_in_ProductService() throws Exception {
       //GIVEN
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .productSKUId(1)
                .productName("Premium A4 Copy Paper")
                .productPrice(14.10)
                .productDescription("High-quality A4 copy paper suitable for home and office use.")
                .categories(List.of("office", "stationery"))
                .productQuantity(3)
                .build()
                ;
        //WHEN
        ResultActions resultActions = getResultActions(post("/product/create"),objectMapper
                .writeValueAsString(productCreateRequest));
        //THEN
        verify(productService, times(1)).createProduct(Mockito.any());
        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(201);
    }

    @Test
    void should_successfully_getProduct_by_invoking_getProductBySku_method_in_ProductService() throws Exception {
        //GIVEN
        int SKU = 1;
        //WHEN
        ResultActions resultActions = mockMvc.perform(get("/product/" + SKU));

        //THEN
        verify(productService, times(1)).getProductBySKUId(Mockito.any());
        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(200);
    }

    @Test
    void should_successfully_getProduct_by_invoking_getAllProducts_method_in_ProductService() throws Exception {

        //GIVEN && WHEN
        ResultActions resultActions = mockMvc.perform(get("/product"));

        //THEN
        verify(productService, times(1)).getAllProducts();
        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(200);
    }

    @Test
    void should_successfully_getProduct_by_invoking_getProductByName_method_in_ProductService() throws Exception {

        //GIVEN
        String productName = "test";
        // WHEN
        ResultActions resultActions = getResultActions(get("/product/find/{}"),objectMapper
                .writeValueAsString(productName));


        //THEN
        verify(productService, times(1)).getProductsByName(Mockito.any());
        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(200);
    }



    @Test
    void should_successfully_updateProductQuantity_by_invoking_updateProductQuantityBySKUId_method_in_ProductService() throws Exception {

        //GIVEN
        Integer sku = 1;
        Integer quantity = 5;
        // WHEN
        ResultActions resultActions = mockMvc.perform(
                put("/product/update")
                        .param("sku", String.valueOf(sku))
                        .param("quantity", String.valueOf(quantity))
        );


        //THEN
        verify(productService, times(1)).updateProductQuantityBySKUId(sku,quantity);
        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(200);
    }


    @NotNull
    private ResultActions getResultActions(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String objectMapper) throws Exception {
        return mockMvc.perform(mockHttpServletRequestBuilder
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper));
    }

}