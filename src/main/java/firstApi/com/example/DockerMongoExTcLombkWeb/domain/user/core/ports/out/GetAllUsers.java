package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetAllUsers {
    List<User> getAllUsers();

}
