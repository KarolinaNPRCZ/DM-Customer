package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserService;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserDAOPort;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserLoginAndSignUpFacade implements UserService {

    private final UserDAOPort userDAOPort;

    public UserLoginAndSignUpFacade(UserDAOPort userDAOPort) {
        this.userDAOPort = userDAOPort;
    }

    @Override
    public UserId createUser(UserDTO userDTO) throws UserEmailArledyExistsException {
        log.info("to ja facade");
        return userDAOPort.save(userDTO);

    }


    @Override
    public UserDTO getUserByUserEmail(String userEmail) throws UserNotFoundException {
        return userDAOPort.getUserByUserEmail(userEmail)
                .orElseThrow(()->new UserNotFoundException(userEmail));
    }
}
