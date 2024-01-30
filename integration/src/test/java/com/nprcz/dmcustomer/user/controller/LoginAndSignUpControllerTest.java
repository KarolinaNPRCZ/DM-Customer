package com.nprcz.dmcustomer.user.controller;

import com.example.DockerMongoExTcLombkWeb.ports.in.UserDAOPort;
import com.example.DockerMongoExTcLombkWeb.ports.out.UserService;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.nprcz.dmcustomer.AbstractIntegrationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginAndSignUpControllerTest extends AbstractIntegrationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDAOPort userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @DynamicPropertySource
    protected static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    }

    @Test
    void should_successfully_save_user_to_database() throws Exception {
        // GIVEN
        String email = "karolina@test3.com";
        String password = "Password10";

        // WHEN
        ResultActions response = mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
                            {
                            "userEmail": "%s",
                            "userPassword": "%s",
                            "confirmUserPassword": "%s"
                            }
                        """.trim(), email, password, password)));
        String contentAsString = response.andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        UserDTO foundUser = userService.getUserDTOByUserEmail(email);
        String foundEncodedPass = foundUser.password();

        // THEN
        assertAll(() -> {
            assertThat(contentAsString).isEqualTo("1");
            assertThat(foundUser).isNotNull();
            assertThat(passwordEncoder.matches(password, foundEncodedPass)).isTrue();
            assertThat(foundUser.email()).isEqualTo(email);
            assertThat(userDAO.getUserDTOByUserEmail(email).isPresent()).isTrue();
            assertThat(userDAO.getUserDTOByUserEmail(email).get()).isEqualTo(foundUser);
        });
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void authorized_user_should_return_user_with_database() throws Exception {
        //GIVEN
        String email = "test@userexists.com";
        userService.createUser(email, "Password10");
        //WHEN && THEN

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/test@userexists.com", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email))
                .andReturn();
    }

    @Test
    @WithMockUser(authorities = "USER")
    void non_authorized_user_should_return_forbidden_access() throws Exception {
        //GIVEN
        String email = "test@test.com";
        userService.createUser(email, "Password10");
        //WHEN && THEN

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/test@test.com", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andReturn();
    }
}
