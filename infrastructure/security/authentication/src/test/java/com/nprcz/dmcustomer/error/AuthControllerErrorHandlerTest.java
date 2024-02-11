package com.nprcz.dmcustomer.error;

import com.nprcz.dmcustomer.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest
@ContextConfiguration(classes = AuthControllerErrorHandlerTestConfig.class)
class AuthControllerErrorHandlerTest {
    private MockMvc mockMvc;

    @Autowired
    private TestController testController;

    @Autowired
    private AuthControllerErrorHandler authControllerErrorHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup()  {
        mockMvc = MockMvcBuilders.standaloneSetup(testController)
                .setControllerAdvice(authControllerErrorHandler)
                .build();

    }

    @Test
    void should_successfully_handle_AuthenticationException() throws Exception {
        //GIVEN
        LoginRequest loginRequest = new LoginRequest(
                "nonexistentUser",
                "nonexistentPassword"
        );
        // WHEN
        ResultActions resultActions = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)));
        String contentAsString = resultActions.andReturn()
                .getResponse()
                .getContentAsString();
        BadCredentialsResponseDTO badCredentialsResponseDTO = objectMapper
                .readValue(
                        contentAsString,
                        BadCredentialsResponseDTO.class
                );

        // THEN
        assertThat(badCredentialsResponseDTO.errors())
                .containsExactlyInAnyOrder("User with e-mail: nonexistentUser not found");
    }

    @Test
    void should_successfully_handle_BadCredentialsException() throws Exception {
        //GIVEN
        LoginRequest loginRequest = new LoginRequest(
                "testEmail",
                "nonexistentPassword"
        );
        // WHEN
        ResultActions resultActions = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)));
        String contentAsString = resultActions.andReturn()
                .getResponse()
                .getContentAsString();
        BadCredentialsResponseDTO badCredentialsResponseDTO = objectMapper
                .readValue(
                        contentAsString,
                        BadCredentialsResponseDTO.class
                );

        // THEN
        assertThat(badCredentialsResponseDTO.errors())
                .containsExactlyInAnyOrder("Wrong password");
    }

}