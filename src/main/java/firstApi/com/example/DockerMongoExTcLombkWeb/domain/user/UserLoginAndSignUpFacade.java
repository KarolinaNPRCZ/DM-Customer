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
    public UserId createUser(String userEmail,String password) throws UserEmailArledyExistsException {
        UserDTO userDTO = UserDTO.builder().email(userEmail).password(password).build();
        log.info("Facade -> Trying to create user");
        return userDAOPort.save(userDTO);

    }


    @Override
    public UserDTO getUserByUserEmail(String userEmail) throws UserNotFoundException {
        return userDAOPort.getUserByUserEmail(userEmail)
                .orElseThrow(()->new UserNotFoundException(userEmail));
    }
}
