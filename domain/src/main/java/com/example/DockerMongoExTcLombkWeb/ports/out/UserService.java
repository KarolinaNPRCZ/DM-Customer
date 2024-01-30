package com.example.DockerMongoExTcLombkWeb.ports.out;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;

public interface UserService {
    Integer createUser(String userEmail, String hashedPassword);

    UserDTO getUserDTOByUserEmail(String email);

}
