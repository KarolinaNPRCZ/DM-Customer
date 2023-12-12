package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.GetUserByEmailDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserFacade;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.UserRepository;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB.UserDAOImpl;
import org.springframework.context.annotation.Bean;


public class UserConfig {
    @Bean
    public UserDAOPort userDAOPort(UserRepository userRepository) {
        return new UserDAOImpl(userRepository);
    }

    @Bean
    public UserFacade UserControllerPort(UserDAOPort userDatabase) {
        return new UserFacade(userDatabase);
    }


}
