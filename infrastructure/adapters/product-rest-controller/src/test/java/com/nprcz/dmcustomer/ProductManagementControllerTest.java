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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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



}