package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.UserEmailArledyExistsException;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;

public interface UserDAOPort {
    UserDTO getUserByUserEmail(String email);

    UserId save(UserDTO userDTO) throws UserEmailArledyExistsException;
    //
}
