package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log4j2
@Service
 class UserDTOMapper implements Function<User,UserDTO> {


    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getUserEmail(),
                user.getUserPassword()
        );
    }
    UserDTO mapToDTO(User user) {
        return apply(user);
    }
}
