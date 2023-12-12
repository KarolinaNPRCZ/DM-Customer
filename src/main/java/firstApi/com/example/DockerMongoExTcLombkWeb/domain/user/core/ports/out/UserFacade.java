package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserMapper;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserControllerPort;
import lombok.extern.log4j.Log4j2;

import static firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserMapper.createFromDTO;
@Log4j2
public class UserFacade implements UserService {

    private final UserDB userDB;


    public UserFacade(UserDB userDB) {
        this.userDB = userDB;
    }

    @Override
    public UserId createUser(UserDTO userDTO) {
        log.info("to ja facade");
        return userDB.save(userDTO);
    }
}
