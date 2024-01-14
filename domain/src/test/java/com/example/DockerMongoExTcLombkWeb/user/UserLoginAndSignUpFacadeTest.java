package com.example.DockerMongoExTcLombkWeb.user;

import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserLoginAndSignUpFacadeTest {

    private final InMemoryDataBaseAdapter inMemoryDataBaseAdapter = new InMemoryDataBaseAdapter();

    private final UserLoginAndSignUpFacade userLoginAndSignUpFacade = new UserLoginAndSignUpFacade(inMemoryDataBaseAdapter);
    @Test
    void should_successfully_createUser() {
        //GIVEN
        String userEmail = "userTest@Test.com";
        String userPassword = "Test123";
        //WHEN
        UserId userIdCreatedUser = userLoginAndSignUpFacade.createUser(userEmail,userPassword);
        UserDTO createdUser = userLoginAndSignUpFacade.getUserDTOByUserEmail(userEmail);
        //THEN
        Assertions.assertEquals(userIdCreatedUser,createdUser.userId());
        Assertions.assertEquals(userEmail,createdUser.email());
        Assertions.assertEquals(userPassword,createdUser.password());
    }
    @Test
    void should_throw_userAlreadyExistsException_createUser() {
        //GIVEN
        UserDTO createUser = UserDTO.builder()
                .userId(new UserId(10))
                .email("userTest@Test.com")
                .password("password").build();
        //WHEN
        inMemoryDataBaseAdapter.save(createUser);
        //THEN
        Assertions.assertThrows(UserAlreadyExistsException.class,()->userLoginAndSignUpFacade.createUser(createUser.email(), createUser.password()));
    }

    @Test
    void getUserDTOByUserEmail_should_throw_UserNotFoundException() {
        //GIVEN && WHEN && THEN
        Assertions.assertThrows(UserNotFoundException.class,
                ()->userLoginAndSignUpFacade.getUserDTOByUserEmail("email"));
    }
    @Test
    void should_successfully_getUserDTOByUserEmail_find_user() {
        //GIVEN
        UserDTO userDTO = UserDTO.builder().userId(new UserId(5)).email("existing@user.com").password("userPassword").build();
        inMemoryDataBaseAdapter.save(userDTO);
        //WHEN
        UserDTO foundUser = userLoginAndSignUpFacade.getUserDTOByUserEmail(userDTO.email());
        //THEN
        Assertions.assertEquals(userDTO,foundUser);
    }
}