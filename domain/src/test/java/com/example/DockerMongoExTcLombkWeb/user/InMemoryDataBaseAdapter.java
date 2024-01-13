package com.example.DockerMongoExTcLombkWeb.user;

import com.example.DockerMongoExTcLombkWeb.ports.in.UserDAOPort;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryDataBaseAdapter implements UserDAOPort {
    private final Map<String,UserDTO> users = new HashMap<>();
    @Override
    public Optional<UserDTO> getUserDTOByUserEmail(String email) {
        return users.values().stream().filter(user -> user.email().equals(email)).findFirst();
    }

    @Override
    public UserId save(UserDTO userDTO) throws UserEmailArledyExistsException {
        if (users.containsKey(userDTO.email())) throw new UserEmailArledyExistsException("User with given e-mail already exists");
        UserDTO userToSave = userDTO.toBuilder().build();
        if (userDTO.userId() == null){
            userToSave = userDTO.toBuilder().userId(new UserId(5)).build();
        }
        users.put(userDTO.email(),userToSave);
        return userToSave.userId();
    }
}
