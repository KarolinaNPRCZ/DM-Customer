package com.nprcz.dmcustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nprcz.dmcustomer.ports.out.product.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ProductManagementController.class)
@ContextConfiguration(classes = ProductManagementControllerTestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductManagementControllerTest {
    //TODO refactor test method

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;


    @Test
    void should_successfully_createProduct_by_invoking_create_product_method_in_ProductService() throws Exception {
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .productSKUId(1)
                .productName("Premium A4 Copy Paper")
                .productPrice(14.10)
                .productDescription("High-quality A4 copy paper suitable for home and office use.")
                .categories(List.of("office", "stationery")).build();

        ResultActions resultActions = mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(productCreateRequest)));

        verify(productService, times(1)).createProduct(Mockito.any());
        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(201);
    }

    @Test
    void should_successfully_getProduct_by_invoking_getProductBySku_method_in_ProductService() throws Exception {
        //GIVEN
        Integer SKU = 1;
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
        ResultActions resultActions = mockMvc.perform(get("/product/find/{}").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(productName)));


        //THEN
        verify(productService, times(1)).getProductByName(Mockito.any());
        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(200);
    }


}