package com.nprcz.dmcustomer.product.controller;

import com.nprcz.dmcustomer.AbstractIntegrationTests;
import com.nprcz.dmcustomer.ports.in.product.ProductDAOPort;
import com.nprcz.dmcustomer.ports.out.product.ProductService;
import com.nprcz.dmcustomer.product.ProductDTO;
import com.nprcz.dmcustomer.utils.productutils.SamplesProductsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class ProductManagementControllerIntegrationTest extends AbstractIntegrationTests implements SamplesProductsResponse {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDAOPort productDAO;

    @DynamicPropertySource
    protected static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
    }

    @AfterEach
    @WithMockUser
    void clearDatabase() {
        productDAO.getAllProducts().forEach(productDTO -> productDAO.deleteProduct(productDTO));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void should_successfully_create_product_in_db() throws Exception {

        //GIVEN && WHEN
        ResultActions resultActions = mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(oneProductDocument().trim()));
        String resultAsString = resultActions.andReturn().getResponse().getContentAsString();
        ProductDTO foundProduct = productService.getProductBySKUId(1);
        // THEN
        assertAll(() -> {
            assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(201);
            assertThat(foundProduct).isNotNull();
            assertThat(foundProduct.id()).isEqualTo(resultAsString);
            assertThat(foundProduct.createdAt()).isNotNull();

        });

    }
    @Test
    void should_return_access_denied_create_product_in_db() throws Exception {

        //GIVEN && WHEN
        ResultActions resultActions = mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(oneProductDocument().trim()));
        // THEN
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(403);

    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void should_return_CONFLICT_caused_by_product_duplication() throws Exception {

        // GIVEN && WHEN
        productService.createProduct(oneProductDocumentDTO());
        ResultActions resultActions = mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(oneProductDocument().trim()));

        // THEN
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(409);

    }

    @Test
    void should_return_NOT_FOUND_caused_by_nonexistent_product_BySKUID() throws Exception {
        // GIVEN
        int sku = 15;
        // WHEN
        ResultActions resultActions = mockMvc.perform(get("/products/" + sku));
        // THEN
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(404);
    }

    @Test
    void should_successfully_return_product_with_specific_SKUId() throws Exception {
        // GIVEN
        ProductDTO productDTO = oneProductDocumentDTO();

        // WHEN
        String SKUIdSavedProduct = productService.createProduct(productDTO);
        ResultActions resultActions = mockMvc.perform(get("/products/" + productDTO.productSKUId()));
        String resultAsString = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        ProductDTO foundProduct = objectMapper.readValue(resultAsString, ProductDTO.class);

        // THEN
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(200);
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct).usingRecursiveComparison().ignoringFields("id", "createdAt").isEqualTo(productDTO);
        assertThat(LocalDateTime.now()).isAfterOrEqualTo(foundProduct.createdAt());
    }

    @Test
    void should_successfully_return_empty_array_because_of_empty_product_database() throws Exception {
        // GIVEN && WHEN
        ResultActions resultActionWithEmptyDB = mockMvc.perform(get("/products"));
        String contentWithEmptyDB = resultActionWithEmptyDB.andReturn().getResponse().getContentAsString();

        // THEN
        assertThat(resultActionWithEmptyDB.andReturn().getResponse().getStatus()).isEqualTo(200);
        assertThat(contentWithEmptyDB).isEqualTo("[]");
    }

    @Test
    void should_successfully_return_all_products_from_database() throws Exception {
        // GIVEN
        List<ProductDTO> withoutID = threeProductDocumentDTOs();
        List<ProductDTO> withID = new ArrayList<>();
        for (ProductDTO dto : withoutID) {
            withID.add(dto.toBuilder().id(UUID.randomUUID().toString()).build());
        }
        withID.forEach(productDTO -> productService.createProduct(productDTO));

        // WHEN
        ResultActions resultAction = mockMvc.perform(get("/products"));
        String content = resultAction.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        // THEN
        assertThat(resultAction.andReturn().getResponse().getStatus()).isEqualTo(200);
        assertThat(content).isNotEmpty();

    }

    @Test
    void should_successfully_return_empty_array_because_none_of_the_products_contain_given_productName() throws Exception {
        // GIVEN && WHEN
        ResultActions resultActionWithEmptyDB = mockMvc.perform(get("/products/search").param("name","non"));
        String contentWithEmptyDB = resultActionWithEmptyDB.andReturn().getResponse().getContentAsString();

        // THEN
        assertThat(resultActionWithEmptyDB.andReturn().getResponse().getStatus()).isEqualTo(200);
        assertThat(contentWithEmptyDB).isEqualTo("[]");
    }

    @Test
    void should_successfully_return_all_products_contains_given_productName() throws Exception {
        // GIVEN
        List<ProductDTO> withoutID = threeProductDocumentDTOs();
        List<ProductDTO> withID = new ArrayList<>();
        for (ProductDTO dto : withoutID) {
            withID.add(dto.toBuilder().id(UUID.randomUUID().toString()).build());
        }
        withID.forEach(productDTO -> productService.createProduct(productDTO));

        // WHEN
        ResultActions resultAction = mockMvc.perform(get("/products/search").param("name","COPY"));
        String content = resultAction.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        // THEN
        assertThat(resultAction.andReturn().getResponse().getStatus()).isEqualTo(200);
        ;
        assertThat(content).isNotEmpty().isNotEqualTo(objectMapper.writeValueAsString(withID)).contains("Copy");
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void should_successfully_update_product_quantity() throws Exception {

        // GIVEN
        ProductDTO productDTO = oneProductDocumentDTO();
        int sku = 1;
        int quantity = 1;

        // WHEN
        String productID=productService.createProduct(productDTO);

        ResultActions resultActions = mockMvc.perform(put("/products/"+sku).param("quantity",String.valueOf(quantity)).contentType(MediaType.APPLICATION_JSON));


        ProductDTO foundProduct = productService.getProductBySKUId(1);
        // THEN
        assertAll(() -> {
            assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(200);
            assertThat(foundProduct).isNotNull();
            assertThat(foundProduct.productQuantity()).isGreaterThan(productDTO.productQuantity());
            assertThat(foundProduct.createdAt()).isNotNull();

        });

    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void should_return_NOT_FOUND_caused_by_nonexistent_product_BySKUID_in_updateQuantity_method() throws Exception {
        // GIVEN WHEN
        ResultActions resultActions = mockMvc
                .perform(put("/products/"+123343)
                        .param("quantity",String.valueOf(4))
                        .contentType(MediaType.APPLICATION_JSON));
        // THEN
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(404);
    }

}
