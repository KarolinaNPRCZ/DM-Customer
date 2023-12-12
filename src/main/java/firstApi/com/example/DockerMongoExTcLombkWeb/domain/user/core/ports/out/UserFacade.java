package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.GetUserByEmailDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserDAOPort;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserFacade implements UserService {

    private final UserDAOPort userDAOPort;



    public UserFacade(UserDAOPort userDAOPort) {
        this.userDAOPort = userDAOPort;

    }

    @Override
    public UserId createUser(UserDTO userDTO) {
        log.info("to ja facade");
        return userDAOPort.save(userDTO);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userDAOPort.getUserByEmail(email);
    }
}
