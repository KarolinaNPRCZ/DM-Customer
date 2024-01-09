package com.example.DockerMongoExTcLombkWeb;

import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.UserMapperInterface;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

 class UserMapperInterfaceImpl implements UserMapperInterface<User> {
    @Override
    public User fromUserDTO(UserDTO userDTO) {
        return new User(userDTO.email(),
                userDTO.password(),
                userDTO.roles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList()));
    }

    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .email(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
