package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOMongoDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserDB;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.UserRepository;
import lombok.extern.log4j.Log4j2;

import static firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserMapper.createFromDTO;
@Log4j2
public class UserDAOImpl implements UserDB {

    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserId save(UserDTO userDTO) {
        log.info("to ja daoimppl");

        User user = createFromDTO(userDTO);
        User savedUser = userRepository.save(user);
        return new UserId(savedUser.getId());
    }
}
