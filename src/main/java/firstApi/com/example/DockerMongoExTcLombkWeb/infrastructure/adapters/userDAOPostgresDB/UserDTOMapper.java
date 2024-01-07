package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserRoleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Service
class UserDTOMapper implements Function<User, UserDTO> {



    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                new UserId(user.getId()),
                user.getUserEmail(),
                user.getUserPassword(),
                getListOfUserRoles(user),//String token
                "token");
    }

    UserDTO mapToDTO(User user) {
        return apply(user);
    }

    List<UserRoleDTO> getListOfUserRoles(User user) {
        return user.getRoles().stream().map(userRole -> new UserRoleDTO(userRole.getName())).collect(Collectors.toList());
    }


}
