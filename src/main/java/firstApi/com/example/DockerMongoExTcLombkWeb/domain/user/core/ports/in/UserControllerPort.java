package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import org.springframework.stereotype.Repository;
//create new User
public interface UserControllerPort {
    UserId createUser(UserDTO userDTO);
}
