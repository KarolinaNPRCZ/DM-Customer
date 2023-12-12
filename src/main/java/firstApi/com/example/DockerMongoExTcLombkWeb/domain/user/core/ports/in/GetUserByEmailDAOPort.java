package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;

public interface GetUserByEmailDAOPort {
    UserDTO getUserByEmail(String email);
    //
}
