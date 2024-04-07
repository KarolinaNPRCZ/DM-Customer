package com.nprcz.dmcustomer.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest
@ContextConfiguration(classes = ProductManagementControllerExceptionHandlerTestConfig.class)
class ProductManagementControllerExceptionHandlerTest {
    @Autowired
    private ProductManagementControllerTest productManagementControllerTest;
    @Autowired
    private ProductManagementControllerExceptionHandler productManagementControllerExceptionHandler;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productManagementControllerTest)
                .setControllerAdvice(productManagementControllerExceptionHandler).build();
    }

    @Test
    void handle_UserEmailAlreadyExistsException_exception() throws Exception {
        //GIVEN && WHEN
        ResultActions resultActions = mockMvc.perform(post("/test/003"));
        ProductExceptionsResponse productExceptionsResponse;


        productExceptionsResponse = getProductAlreadyExistsResponse(resultActions);

        // THEN
        assertThat(productExceptionsResponse.httpStatus()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(productExceptionsResponse.description()).isEqualTo("Product with SKU: 2 already exist");

    }



    @Test
    void handle_UserNotFoundException_exception() throws Exception {
        //GIVEN && WHEN
        ResultActions resultActions = mockMvc.perform(post("/test/004"));


        ProductExceptionsResponse productExceptionsResponse = getProductAlreadyExistsResponse(resultActions);

        // THEN
        assertThat(productExceptionsResponse.httpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(productExceptionsResponse.description()).isEqualTo("Product with SKU: 2 not found");

    }
    @Test
    void handle_InvalidProductQuantityException() throws Exception {
        //GIVEN && WHEN
        ResultActions resultActions = mockMvc.perform(post("/test/005"));


        ProductExceptionsResponse productExceptionsResponse = getProductAlreadyExistsResponse(resultActions);

        // THEN
        assertThat(productExceptionsResponse.httpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(productExceptionsResponse.description()).isEqualTo("Product quantity after update cannot be less than 0. Actual product quantity: 2");

    }
    private ProductExceptionsResponse getProductAlreadyExistsResponse(ResultActions resultActions) throws UnsupportedEncodingException, JsonProcessingException {

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, ProductExceptionsResponse.class);
    }


}