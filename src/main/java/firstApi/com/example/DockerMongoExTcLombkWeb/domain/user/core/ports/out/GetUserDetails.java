package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface GetUserDetails {
  User getUserByEmail(String email);
}
