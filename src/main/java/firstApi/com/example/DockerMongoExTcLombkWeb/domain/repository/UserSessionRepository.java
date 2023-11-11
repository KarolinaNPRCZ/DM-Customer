package firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession,Long> {
}
