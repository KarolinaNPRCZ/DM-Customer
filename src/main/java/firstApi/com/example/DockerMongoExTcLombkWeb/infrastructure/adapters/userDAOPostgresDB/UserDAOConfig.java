package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserDAOPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
 class UserDAOConfig {

    @Bean
    UserDAOPort userDAOImpl(UserRepository userRepository, UserDTOMapper userDTOMapper, UserMapper userMapper) {
        return new UserDAOImpl(userRepository,userDTOMapper, userMapper);
    }

}
