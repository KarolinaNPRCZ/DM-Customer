package com.nprcz.dmcustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = AuthController.class)
@ContextConfiguration(classes = AuthControllerTestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtAuthenticator jwtAuthenticator;


    @Test
    void should_successfully_return_token() throws Exception {
        // GIVEN
        LoginRequest offerSaveRequest = new LoginRequest(
                "testEmail",
                "testPassword"
        );

        // WHEN
        ResultActions resultActions = mockMvc.perform(post("/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(offerSaveRequest))
                .characterEncoding(StandardCharsets.UTF_8));

        // THEN
        assertThat(resultActions.andReturn().getResponse().getStatus())
                .isEqualTo(200);
        verify(jwtAuthenticator, times(1))
                .authenticate(Mockito.any());
    }
}