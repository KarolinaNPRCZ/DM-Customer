package com.example.DockerMongoExTcLombkWeb.ports.out;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserId;

public interface UserService {
    UserId createUser(String userEmail, String hashedPassword);

    UserDTO getUserDTOByUserEmail(String email);

}
