package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOMongoDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserDB;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.UserRepository;

public class UserDAOImpl implements UserDAO, UserDB {

    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserId save(User user) {
        User savedUser = userRepository.save(user);
        return new UserId(savedUser.getId());
    }
}
