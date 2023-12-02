package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserControllerPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserDB;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserFacade;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.UserRepository;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOMongoDB.UserDAOImpl;
import org.springframework.context.annotation.Bean;

public class UserConfig {
        @Bean
        public UserDB userDatabase(UserRepository userRepository){
            return new UserDAOImpl(userRepository);
        }
        @Bean
        public UserControllerPort UserControllerPort(UserDB userDatabase){
            return new UserFacade(userDatabase);
        }
    }
