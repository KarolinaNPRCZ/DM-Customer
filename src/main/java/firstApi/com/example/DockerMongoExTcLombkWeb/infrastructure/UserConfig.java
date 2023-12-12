package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserDB;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserFacade;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.UserRepository;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB.UserDAOImpl;
import org.springframework.context.annotation.Bean;


public class UserConfig {
    @Bean
    public UserDB userDatabase(UserRepository userRepository) {
        return new UserDAOImpl(userRepository);
    }

    @Bean
    public UserFacade UserControllerPort(UserDB userDatabase) {
        return new UserFacade(userDatabase);
    }


/*    spring.security.user.name= user
    spring.security.user.password= password
    spring.security.user.roles= USER*/

}
