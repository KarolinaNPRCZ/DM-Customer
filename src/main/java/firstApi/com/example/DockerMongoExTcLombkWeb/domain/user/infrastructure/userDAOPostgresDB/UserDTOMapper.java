package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log4j2
@Service
 class UserDTOMapper implements Function<User,UserDTO> {


    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getUserEmail(),
                user.getUserPassword()
        );
    }
    UserDTO mapToDTO(User user) {
        return apply(user);
    }
}
