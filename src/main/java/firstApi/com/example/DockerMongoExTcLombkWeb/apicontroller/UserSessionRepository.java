package firstApi.com.example.DockerMongoExTcLombkWeb.apicontroller;

import firstApi.com.example.DockerMongoExTcLombkWeb.apicontroller.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSessionRepository extends MongoRepository<UserSession,String> {
}
