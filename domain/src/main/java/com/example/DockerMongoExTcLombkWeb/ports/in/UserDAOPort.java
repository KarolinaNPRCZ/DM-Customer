package com.example.DockerMongoExTcLombkWeb.ports.in;

import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserId;
import com.example.DockerMongoExTcLombkWeb.user.UserEmailArledyExistsException;

import java.util.Optional;

public interface UserDAOPort {
    Optional<UserDTO> getUserDTOByUserEmail(String email);

    UserId save(UserDTO userDTO) throws UserEmailArledyExistsException;
    //
}
