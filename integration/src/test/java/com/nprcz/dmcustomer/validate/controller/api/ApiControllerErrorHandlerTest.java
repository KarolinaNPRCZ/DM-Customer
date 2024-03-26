package com.nprcz.dmcustomer.validate.controller.api;

import com.nprcz.dmcustomer.AbstractIntegrationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ApiControllerErrorHandlerTest extends AbstractIntegrationTests {
    @DynamicPropertySource
    protected static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
    }

    @Autowired
    private MessageSource messageSource;
    private String fieldMustBeNotNull;
    private String fieldMustBeNotBlank;
    private String invalidFormat;
    private String wrongUserEmail;
    private String fieldMustBeEqual;
    private String wrongPassword;

    private String validationPositiveOrZero;
    private String validationDecimalMin;
    private String validationDecimalMax;


    @BeforeEach
    void setUp() {
        this.fieldMustBeNotBlank = messageSource.getMessage(
                "not.blank", null, Locale.ENGLISH
        );
        this.fieldMustBeEqual = messageSource.getMessage(
                "not.equal", null, Locale.ENGLISH
        );
        this.fieldMustBeNotNull = messageSource.getMessage(
                "not.null", null, Locale.ENGLISH
        );
        this.invalidFormat = messageSource.getMessage(
                "invalid.format", null, Locale.ENGLISH
        );
        this.wrongUserEmail = messageSource.getMessage(
                "wrong.email", null, Locale.ENGLISH
        );
        this.wrongPassword = messageSource.getMessage(
                "wrong.password", null, Locale.ENGLISH
        );
        this.validationPositiveOrZero = messageSource.getMessage(
                "validation.positive.or.zero", null, Locale.ENGLISH
        );
        this.validationDecimalMin = messageSource.getMessage(
                "validation.decimal.min", null, Locale.ENGLISH
        );
        this.validationDecimalMax = messageSource.getMessage(
                "validation.decimal.max", null, Locale.ENGLISH
        );
    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_empty_content() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""));
        String message = response.andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        assertThat(message).isEqualTo(invalidFormat);
    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_wrong_format_content() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("invalid"));
        String message = response.andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        assertThat(message).isEqualTo(invalidFormat);
    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_missing_parameters_of_user_register_request() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));

        // THEN
        response.andExpectAll(
                jsonPath(
                        "$.errors[?(@.field=='userEmail')].messages[*]",
                        containsInAnyOrder(fieldMustBeNotBlank, fieldMustBeNotNull)
                ),
                jsonPath(
                        "$.errors[?(@.field=='userPassword')].messages[*]",
                        containsInAnyOrder(fieldMustBeNotBlank, fieldMustBeNotNull)
                ),
                jsonPath(
                        "$.errors[?(@.field=='confirmUserPassword')].messages[*]",
                        containsInAnyOrder(fieldMustBeNotBlank, fieldMustBeNotNull)
                )
        );

    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_not_equals_passwords_of_user_register_request() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
                            {
                            "userEmail": "%s",
                            "userPassword": "%s",
                            "confirmUserPassword": "%s"
                            }
                        """.trim(), "test@notequal.com", "Password10", "Password")));
        // THEN
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors[0].field").value("userPasswordConfirmed"))
                .andExpect(jsonPath("$.errors[0].messages[0]").value(fieldMustBeEqual));

    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_not_matches_email_of_user_register_request() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
                            {
                            "userEmail": "%s",
                            "userPassword": "%s",
                            "confirmUserPassword": "%s"
                            }
                        """.trim(), "testnomatches.com", "Password10", "Password10")));
        //THEN
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors[0].field").value("userEmail"))
                .andExpect(jsonPath("$.errors[0].messages[0]").value(wrongUserEmail));

    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_not_matches_password_of_user_register_request() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
                            {
                            "userEmail": "%s",
                            "userPassword": "%s",
                            "confirmUserPassword": "%s"
                            }
                        """.trim(), "test@matches.com", "Pas1", "Pas1")));
        // THEN
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors[0].field").value("userPassword"))
                .andExpect(jsonPath("$.errors[0].messages[0]").value(wrongPassword));


    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_missing_parameters_of_product_create_request() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));

        // THEN
        response.andExpectAll(
                jsonPath(
                        "$.errors[?(@.field=='productSKUId')].messages[*]",
                        containsInAnyOrder(fieldMustBeNotNull)
                ),
                jsonPath(
                        "$.errors[?(@.field=='productPrice')].messages[*]",
                        containsInAnyOrder(fieldMustBeNotNull)
                ),
                jsonPath(
                        "$.errors[?(@.field=='productName')].messages[*]",
                        containsInAnyOrder(fieldMustBeNotBlank, fieldMustBeNotNull)
                ),
                jsonPath(
                        "$.errors[?(@.field=='productDescription')].messages[*]",
                        containsInAnyOrder(fieldMustBeNotNull)
                ),

                jsonPath(
                        "$.errors[?(@.field=='categories')].messages[*]",
                        containsInAnyOrder(fieldMustBeNotNull)
                )
        );

    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_not_matches_productSKUId_of_product_register_request() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                       {
                        "sku": -52,
                        "name": "Premium A4 Copy Paper",
                        "price": 14.10,
                        "description": "High-quality A4 copy paper suitable for home and office use.",
                        "categories": ["office", "stationery"]
       
                       }
                            """.trim()));
        // THEN
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(
                        "$.errors[?(@.field=='productSKUId')].messages[*]",
                        containsInAnyOrder(validationPositiveOrZero)
                ));


    }
    @Test
    void should_handle_BAD_REQUEST_caused_by_not_matches_min_productPrice_of_product_register_request() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                       {
                        "sku": 2,
                        "name": "Premium A4 Copy Paper",
                        "price": -14.10,
                        "description": "High-quality A4 copy paper suitable for home and office use.",
                        "categories": ["office", "stationery"]
       
                       }
                            """.trim()));
        // THEN
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(
                        "$.errors[?(@.field=='productPrice')].messages[*]",
                        containsInAnyOrder(validationDecimalMin.replace("{value}","0.00"))
                ));


    }

    @Test
    void should_handle_BAD_REQUEST_caused_by_not_matches_max_productPrice_of_product_register_request() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                       {
                        "sku": 2,
                        "name": "Premium A4 Copy Paper",
                        "price": 14000.10,
                        "description": "High-quality A4 copy paper suitable for home and office use.",
                        "categories": ["office", "stationery"]
       
                       }
                            """.trim()));
        // THEN
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(
                        "$.errors[?(@.field=='productPrice')].messages[*]",
                        containsInAnyOrder(validationDecimalMax.replace("{value}","10000.00"))
                ));


    }

}
