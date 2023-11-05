package firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSessionRepository extends MongoRepository<UserSession,String> {
}
