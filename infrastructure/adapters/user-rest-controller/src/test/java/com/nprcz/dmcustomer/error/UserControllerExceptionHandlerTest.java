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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest
@ContextConfiguration(classes = UserControllerExceptionHandlerTestConfig.class)
class UserControllerExceptionHandlerTest {
    @Autowired
    private UserLoginAndSignUpControllerTest userLoginAndSignUpControllerTest;
    @Autowired
    private UserControllerExceptionHandler userControllerExceptionHandler;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userLoginAndSignUpControllerTest)
                .setControllerAdvice(userControllerExceptionHandler).build();
    }

    @Test
    void handle_UserEmailAlreadyExistsException_exception() throws Exception {
        //GIVEN && WHEN
        ResultActions resultActions = mockMvc.perform(post("/test/001"));
        UserAlreadyExistsResponse userAlreadyExistsResponse;


        userAlreadyExistsResponse = getUserAlreadyExistsResponse(resultActions);

        // THEN
        assertThat(userAlreadyExistsResponse.httpStatus()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(userAlreadyExistsResponse.description()).isEqualTo("User with given e-mail already exists");

    }



    @Test
    void handle_UserNotFoundException_exception() throws Exception {
        //GIVEN && WHEN
        ResultActions resultActions = mockMvc.perform(post("/test/002"));


        UserAlreadyExistsResponse userAlreadyExistsResponse = getUserAlreadyExistsResponse(resultActions);

        // THEN
        assertThat(userAlreadyExistsResponse.httpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(userAlreadyExistsResponse.description()).isEqualTo("User with e-mail:  not found");

    }
    private UserAlreadyExistsResponse getUserAlreadyExistsResponse(ResultActions resultActions) throws UnsupportedEncodingException, JsonProcessingException {

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, UserAlreadyExistsResponse.class);
    }


}