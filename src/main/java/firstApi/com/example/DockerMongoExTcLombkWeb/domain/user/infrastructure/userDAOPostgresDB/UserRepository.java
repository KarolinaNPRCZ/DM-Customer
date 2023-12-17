package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 interface UserRepository extends JpaRepository<User, String> {
    User getUserByUserEmail(String userEmail);
}
