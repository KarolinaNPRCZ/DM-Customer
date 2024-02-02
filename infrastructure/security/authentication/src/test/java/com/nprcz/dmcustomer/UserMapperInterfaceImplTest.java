package com.nprcz.dmcustomer;


import com.nprcz.dmcustomer.user.DTO.UserDTO;
import com.nprcz.dmcustomer.user.DTO.UserRoleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperInterfaceImplTest {
    private UserMapperInterfaceImpl userMapperInterface;
    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        this.userMapperInterface = new UserMapperInterfaceImpl();
        String email = "Email@Test.com";
        String password = "Password10";
        String role = "USER";
        user = new User(email,
                password,
                Collections.singleton(new SimpleGrantedAuthority(role)));

        userDTO = UserDTO.builder()
                .email(email)
                .password(password)
                .roles(List.of(new UserRoleDTO(role))).build();


    }

    @Test
    void should_successfully_map_User_fromUserDTO() {
        //WHEN
        User userAfterMapp = userMapperInterface.fromUserDTO(userDTO);
        //THEN
        assertThat(user.getUsername()).isEqualTo(userAfterMapp.getUsername());
        assertThat(user.getPassword()).isEqualTo(userAfterMapp.getPassword());
        assertThat(user.getAuthorities().stream().findFirst().toString()).isEqualTo(userAfterMapp.getAuthorities().stream().findFirst().toString());
    }

    @Test
    void apply() {
        //WHEN
        UserDTO userDTOAfterApply = userMapperInterface.apply(user);
        //THEN
        assertThat(userDTO.email()).isEqualTo(userDTOAfterApply.email());
        assertThat(userDTO.password()).isEqualTo(userDTOAfterApply.password());

    }
}