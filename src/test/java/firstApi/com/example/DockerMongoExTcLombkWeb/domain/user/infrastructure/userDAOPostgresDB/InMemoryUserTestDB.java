package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.UserEmailArledyExistsException;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserDAOPort;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserTestDB implements UserDAOPort {
    private final Map<String,UserDTO> inMemoryDataBase = new HashMap<>();
    @Override
    public Optional<UserDTO> getUserByUserEmail(String email) {
        return null;
    }

    @Override
    public UserId save(@NotNull UserDTO userDTO)  {
        if (inMemoryDataBase.containsKey(userDTO.email())){
            try {
                throw new UserEmailArledyExistsException(String
                        .format("User with email: %s already exists",userDTO.email()));
            } catch (UserEmailArledyExistsException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }
}
