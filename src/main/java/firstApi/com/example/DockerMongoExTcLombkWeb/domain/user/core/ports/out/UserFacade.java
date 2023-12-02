package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserMapper;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserControllerPort;

import static firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserMapper.createFromDTO;

public class UserFacade implements UserControllerPort {

    private final UserDB userDB;


    public UserFacade(UserDB userDB) {
        this.userDB = userDB;
    }

    @Override
    public UserId createUser(UserDTO userDTO) {
        User user = createFromDTO(userDTO);
        return userDB.save(user);
    }
}
