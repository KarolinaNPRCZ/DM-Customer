package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.security;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

 class UserMapperInterfaceImpl implements firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.UserMapperInterface<User> {
    @Override
    public User fromUserDTO(UserDTO userDTO) {
        return new User(userDTO.email(),
                userDTO.password(),
                userDTO.roles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.name().name()))
                        .collect(Collectors.toList()));
    }

    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .email(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
