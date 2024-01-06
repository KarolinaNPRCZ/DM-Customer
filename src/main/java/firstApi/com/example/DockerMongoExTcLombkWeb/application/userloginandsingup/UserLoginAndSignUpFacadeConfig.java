package firstApi.com.example.DockerMongoExTcLombkWeb.application.userloginandsingup;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserTokenService;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.UserLoginAndSignUpFacade;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserService;
import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.security.JwtService;
import jakarta.persistence.Column;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class UserLoginAndSignUpFacadeConfig {

    @Bean
    UserService UserControllerPort(UserDAOPort userDAOPort) {
        return new UserLoginAndSignUpFacade(userDAOPort);
    }


}
