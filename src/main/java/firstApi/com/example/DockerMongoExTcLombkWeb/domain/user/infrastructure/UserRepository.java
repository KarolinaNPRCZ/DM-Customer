package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
     User getUserByEmail(String email);
}
