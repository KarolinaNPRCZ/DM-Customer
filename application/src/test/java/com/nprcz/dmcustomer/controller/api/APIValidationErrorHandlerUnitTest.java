package com.nprcz.dmcustomer.controller.api;

import com.nprcz.dmcustomer.messages.MessageConfig;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration(classes = {
        APIValidationErrorHandlerUnitTestConfig.class,
        MessageConfig.class
})
@SpringBootTest
class APIValidationErrorHandlerUnitTest {

    @Autowired
    private ControllerTest controllerTest;

    @Autowired
    private MessageSource messageSource;

    private MockMvc mockMvc;

    @Autowired
    private APIValidationErrorHandler apiValidationErrorHandler;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controllerTest)
                .setControllerAdvice(apiValidationErrorHandler)
                .build();
    }

    @Test
    void should_handle_MethodArgumentNotValidException() throws Exception {
        // GIVEN && WHEN
        String message = getResultActions(post("/test-exception"), "{}").andReturn()
                .getResponse()
                .getContentAsString();


        // THEN
        assertThat(message).contains("userEmailNotNullTestMessages");

    }

    @Test
    void should_handle_HttpMessageNotReadableException() throws Exception {
        // GIVEN && WHEN
        String contentAsString = getResultActions(post("/test-exception"), "")
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        assertThat(contentAsString)
                .contains(messageSource.getMessage("invalid.format", null, Locale.ENGLISH));
    }

    @Test
    void should_handle_MethodArgumentTypeMismatchException() throws Exception {
        // GIVEN
        Object firstArg = "abc";
        Object secondArg = "Integer";
        Object[] args = new Object[]{firstArg, secondArg};
        String message = messageSource.getMessage("invalid.type", args, Locale.ENGLISH);

        // WHEN
        String contentAsString = mockMvc.perform(get("/test-exception/abc"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        assertThat(contentAsString).contains(message);
    }

    @Test
    void should_handle_wrong_email_pattern() throws Exception {
        //GIVEN
        String request = String.format("""
                    {
                    "userEmailTest": "%s",
                    "userPassword": "%s",
                    "confirmUserPassword": "%s"
                    }
                """.trim(), "testnomatches.com", "Password10", "Password10");
        // WHEN
        String message = getResultActions(post("/test-exception"), request)
                .andReturn()
                .getResponse()
                .getContentAsString();
        // THEN
        assertThat(message).contains("userEmailWrongPatternTestMessage");
    }

    @Test
    void should_handle_wrong_password_pattern() throws Exception {
        // GIVEN
        String request = String.format("""
                    {
                    "userEmailTest": "%s",
                    "userPassword": "%s",
                    "confirmUserPassword": "%s"
                    }
                """.trim(), "correct@email.com", "Pas", "Pas");
        // WHEN
       String message = getResultActions(post("/test-exception"), request)
                .andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        assertThat(message).contains("userPasswordWrongPatternTestMessage");
    }

    @Test
    void should_handle_not_equal_passwords() throws Exception {
        // GIVEN
        String request = String.format("""
                    {
                    "userEmailTest": "%s",
                    "userPassword": "%s",
                    "confirmUserPassword": "%s"
                    }
                """.trim(), "correct@email.com", "Password10", "Password1");
        // WHEN
        String message = getResultActions(post("/test-exception"), request).andReturn()
                .getResponse()
                .getContentAsString();

        // THEN
        assertThat(message).contains("PasswordNonEqualTestMessages");
    }

    @NotNull
    private ResultActions getResultActions(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String content) throws Exception {
        return mockMvc.perform(mockHttpServletRequestBuilder
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
    }
}