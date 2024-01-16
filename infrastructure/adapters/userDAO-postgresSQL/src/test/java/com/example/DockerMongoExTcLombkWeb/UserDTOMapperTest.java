package com.example.DockerMongoExTcLombkWeb;

import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserRoleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserDTOMapperTest {
    private User user;
    private UserDTOMapper userDTOMapper;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        this.userDTOMapper = new UserDTOMapper();
        String userEmail = "test@test.com";
        String userPassword = "Password10";
        userDTO = UserDTO.builder()
                .email(userEmail)
                .password(userPassword)
                .roles(List.of(new UserRoleDTO("USER")))
                .build();
        user = new User(userEmail, userPassword);
        user.setRoles(List.of(new UserRole(1, Role.USER, null)));
    }

    @Test
    void should_successfully_apply_to_UserDTO() {
        // GIVEN && WHEN
        UserDTO appliedUser = userDTOMapper.apply(user);

        // THEN
        assertThat(appliedUser.email()).isEqualTo(userDTO.email());
        assertThat(appliedUser.password()).isEqualTo(userDTO.getPassword());
    }

    @Test
    void should_successfully_map_to_UserDTO() {
        // GIVEN && WHEN
        UserDTO appliedUser = userDTOMapper.mapToDTO(user);

        // THEN
        assertThat(appliedUser.email()).isEqualTo(userDTO.email());
        assertThat(appliedUser.password()).isEqualTo(userDTO.getPassword());
    }

    @Test
    void getListOfUserRoles() {
        //GIVEN && WHEN
        UserDTO appliedUser = userDTOMapper.mapToDTO(user);
        //THEN
        assertThat(appliedUser.roles().get(0).name()).isEqualTo(userDTO.roles().get(0).name());
    }
}