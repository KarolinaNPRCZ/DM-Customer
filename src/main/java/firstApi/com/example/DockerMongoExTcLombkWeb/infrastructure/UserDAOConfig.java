package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.UserRepository;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB.UserDAOImpl;
import org.springframework.context.annotation.Bean;


public class UserDAOConfig {

    @Bean
    UserDAOPort userDAOImpl(UserRepository userRepository) {
        return new UserDAOImpl(userRepository);
    }

}
