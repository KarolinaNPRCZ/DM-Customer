package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.TokenDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserRoleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Service
class UserDTOMapper implements Function<User, UserDTO> {
    private TokenDTOMapper tokenDTOMapper;


    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                new UserId(user.getId()),
                user.getUserEmail(),
                user.getUserPassword(),
                getListOfUserRoles(user),
                getListOfTokens(user))
                ;
    }

    UserDTO mapToDTO(User user) {
        return apply(user);
    }

    List<UserRoleDTO> getListOfUserRoles(User user) {
        return user.getRoles().stream().map(userRole -> new UserRoleDTO(userRole.getName())).collect(Collectors.toList());
    }

    List<TokenDTO> getListOfTokens(User user) {
        return user.getTokens().stream().map(token -> tokenDTOMapper.mapToDTO(token)).collect(Collectors.toList());
    }

}
