package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
 interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByUserEmail(String userEmail);
}
