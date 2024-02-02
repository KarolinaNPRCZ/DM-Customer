package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.user.DTO.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserEntityMapperTest {
    private UserEntityMapper userEntityMapper;
    private UserDTO userDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        this.userEntityMapper = new UserEntityMapper();
        String userEmail = "test@test.com";
        String userPassword = "Password10";
        userDTO = UserDTO.builder()
                .email(userEmail)
                .password(userPassword)
                .build();
        user = new User(userEmail, userPassword);
    }

    @Test
    void should_successfully_apply_to_UserDTO() {
        // GIVEN && WHEN
        User appliedUser = userEntityMapper.apply(userDTO);

        // THEN
        assertThat(appliedUser.getUserEmail()).isEqualTo(user.getUserEmail());
        assertThat(appliedUser.getUserPassword()).isEqualTo(user.getUserPassword());
    }

    @Test
    void should_successfully_map_to_UserDTO() {
        // GIVEN && WHEN
        User appliedUser = userEntityMapper.mapToUser(userDTO);

        // THEN
        assertThat(appliedUser.getUserEmail()).isEqualTo(user.getUserEmail());
        assertThat(appliedUser.getUserPassword()).isEqualTo(user.getUserPassword());
    }

}