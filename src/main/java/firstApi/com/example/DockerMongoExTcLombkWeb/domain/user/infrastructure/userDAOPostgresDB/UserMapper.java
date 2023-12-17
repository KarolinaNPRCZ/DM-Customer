package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log4j2
@Service
 class UserMapper implements Function<UserDTO,User> {


    @Override
    public User apply(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.email(),
                userDTO.password()
        );
    }
    User mapToUser(UserDTO userDTO) {
        return apply(userDTO);
    }
}
