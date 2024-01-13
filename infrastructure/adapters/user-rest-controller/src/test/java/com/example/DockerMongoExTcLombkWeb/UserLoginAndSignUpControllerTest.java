package com.example.DockerMongoExTcLombkWeb;

import com.example.DockerMongoExTcLombkWeb.ports.out.UserService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = UserLoginAndSignUpController.class)
@ContextConfiguration(classes = UserLoginAndSignUpControllerTestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
class UserLoginAndSignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Test
    void should_successfully_createUser_by_invoking_createUser_method_in_UserService() throws Exception {
        //GIVEN
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest("karolina@admin.com", "Password10", "Password10");

        //WHEN
        ResultActions resultActions = mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegisterRequest)));
        //THEN
        verify(userService, times(1)).createUser(Mockito.any(),Mockito.any());

        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(201);

    }
    @Test
    void should_successfully_findUser_by_invoking_getUserByUserEmail_method_in_UserService() throws Exception {
        //GIVEN
        String email = "karolina@admin.com";
        //WHEN
        ResultActions resultActions = mockMvc.perform(get("/users/{}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(email)));
        //THEN
        verify(userService, times(1)).getUserDTOByUserEmail(Mockito.any());

        assertThat(resultActions.andReturn()
                .getResponse()
                .getStatus())
                .isEqualTo(200);

    }


}