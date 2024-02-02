package com.nprcz.dmcustomer.validate.controller.auth;

import com.nprcz.dmcustomer.error.BadCredentialsResponseDTO;
import com.nprcz.dmcustomer.ports.out.UserService;
import com.nprcz.dmcustomer.AbstractIntegrationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerErrorHandlerTest extends AbstractIntegrationTests {

    @DynamicPropertySource
    protected static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    }

    @Autowired
    private UserService userService;

    @Test
    void should_return_UNAUTHORIZED_because_of_nonexistent_user_in_database() throws Exception {
        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
                            {
                            "userEmail": "%s",
                            "userPassword": "%s",
                            "confirmUserPassword": "%s"
                            }
                        """.trim(), "test@nonexistsuser.com", "Password10", "Password10")));
        String responseAsString = response.andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse()
                .getContentAsString();
        BadCredentialsResponseDTO apiErrorDTO = objectMapper.readValue(responseAsString, BadCredentialsResponseDTO.class);

        // THEN
        assertThat(apiErrorDTO.errors()).contains("User with e-mail: test@nonexistsuser.com not found");
    }

    @Test
    void should_return_UNAUTHORIZED_because_of_wrong_password_to_given_user() throws Exception {
        // GIVEN
        userService.createUser("test@existsuser.com", "Password10");

        // GIVEN && WHEN
        ResultActions response = mockMvc.perform(post("/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
                            {
                            "userEmail": "%s",
                            "userPassword": "%s",
                            "confirmUserPassword": "%s"
                            }
                        """.trim(), "test@existsuser.com", "Password11", "Password11")));
        String responseAsString = response.andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse()
                .getContentAsString();
        BadCredentialsResponseDTO apiErrorDTO = objectMapper.readValue(responseAsString, BadCredentialsResponseDTO.class);

        // THEN
        assertThat(apiErrorDTO.errors()).contains("Wrong e-mail or Password for: test@existsuser.com");
    }
}
