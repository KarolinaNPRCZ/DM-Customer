package com.example.DockerMongoExTcLombkWeb.userloginandsingup;

import com.example.DockerMongoExTcLombkWeb.ports.in.UserDAOPort;
import com.example.DockerMongoExTcLombkWeb.ports.out.UserService;
import com.example.DockerMongoExTcLombkWeb.user.UserLoginAndSignUpFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserLoginAndSignUpFacadeConfig {

    @Bean
    UserService UserControllerPort(UserDAOPort userDAOPort) {
        return new UserLoginAndSignUpFacade(userDAOPort);
    }


}
