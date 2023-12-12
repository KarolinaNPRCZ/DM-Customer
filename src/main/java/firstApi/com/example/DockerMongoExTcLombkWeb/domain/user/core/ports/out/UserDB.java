package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;


public interface UserDB {
    UserId save(UserDTO userDTO);
}
