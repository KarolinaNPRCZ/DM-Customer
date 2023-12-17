package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserFacade;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserService;
import org.springframework.context.annotation.Bean;


public class UserConfig {
    @Bean
    UserService UserControllerPort(UserDAOPort userDAOPort) {
        return new UserFacade(userDAOPort);
    }


}
