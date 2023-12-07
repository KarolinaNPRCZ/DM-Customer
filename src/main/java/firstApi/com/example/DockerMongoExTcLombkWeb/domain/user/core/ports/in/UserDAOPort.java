package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;

import java.util.List;

public interface UserDAOPort {
    List<User> getAllUsers();
    User getUserById( Long id);
    User save(User user);
    //
}
