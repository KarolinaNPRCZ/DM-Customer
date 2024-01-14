package com.example.DockerMongoExTcLombkWeb.user;

import com.example.DockerMongoExTcLombkWeb.ports.in.UserDAOPort;
import com.example.DockerMongoExTcLombkWeb.ports.out.UserService;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserId;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserLoginAndSignUpFacade implements UserService {

    private final UserDAOPort userDAOPort;


    public UserLoginAndSignUpFacade(UserDAOPort userDAOPort) {
        this.userDAOPort = userDAOPort;

    }

    @Override
    public UserId createUser(String userEmail, String password) throws UserAlreadyExistsException {
        UserDTO userDTO = UserDTO.builder().email(userEmail).password(password).build();
        log.info("Facade -> Trying to create user");
        return userDAOPort.save(userDTO);

    }


    @Override
    public UserDTO getUserDTOByUserEmail(String userEmail) throws UserNotFoundException {
        log.info("Facade -> Trying to get userDTO by e-mail");
        return userDAOPort.getUserDTOByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));
    }


}

