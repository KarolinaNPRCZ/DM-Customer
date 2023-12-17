package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.UserEmailArledyExistsException;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserDAOPort;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserTestDB implements UserDAOPort {
    private final Map<String,UserDTO> inMemoryDataBase = new HashMap<>();
    @Override
    public UserDTO getUserByUserEmail(String email) {
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
