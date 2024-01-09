package com.example.DockerMongoExTcLombkWeb;

import com.example.DockerMongoExTcLombkWeb.ports.in.UserDAOPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
 class UserDAOConfig {

    @Bean
    UserDAOPort userDAOImpl(UserRepository userRepository,
                            UserDTOMapper userDTOMapper,
                            UserEntityMapper userEntityMapper,
                            UserRoleRepository userRoleRepository) {

        return new UserDAOImpl(userRepository,userDTOMapper, userEntityMapper,userRoleRepository);
    }


}
