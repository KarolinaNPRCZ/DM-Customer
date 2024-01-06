package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserDAOPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaAuditing
@EnableJpaRepositories
 class UserDAOConfig {

    @Bean
    UserDAOPort userDAOImpl(UserRepository userRepository, UserDTOMapper userDTOMapper, UserEntityMapper userEntityMapper, UserRoleRepository userRoleRepository) {
        return new UserDAOImpl(userRepository,userDTOMapper, userEntityMapper,userRoleRepository);
    }
    @Bean
    UserTokenServiceImpl userTokenService(TokenRepository tokenRepository,TokenDTOMapper tokenDTOMapper,TokenEntityMapper tokenEntityMapper,UserEntityMapper userEntityMapper){
        return new UserTokenServiceImpl(tokenRepository,tokenDTOMapper, tokenEntityMapper, userEntityMapper);
    }

}
