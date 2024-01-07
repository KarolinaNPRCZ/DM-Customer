package firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.UserEmailArledyExistsException;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;

import java.util.Optional;

public interface UserDAOPort {
    Optional<UserDTO> getUserDTOByUserEmail(String email);

    UserId save(UserDTO userDTO) throws UserEmailArledyExistsException;
    //
}
