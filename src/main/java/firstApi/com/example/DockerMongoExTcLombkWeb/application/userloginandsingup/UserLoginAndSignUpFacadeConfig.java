package firstApi.com.example.DockerMongoExTcLombkWeb.application.userloginandsingup;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserService;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.UserLoginAndSignUpFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserLoginAndSignUpFacadeConfig {

    @Bean
    UserService UserControllerPort(UserDAOPort userDAOPort) {
        return new UserLoginAndSignUpFacade(userDAOPort);
    }


}
