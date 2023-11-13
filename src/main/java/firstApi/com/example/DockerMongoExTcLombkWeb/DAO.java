package firstApi.com.example.DockerMongoExTcLombkWeb;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.UserSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

     List<T> getAllUserSessions() ;
     Optional<T> getUserSessionById(Long id) throws SQLException;
     int createUserSession(T t);
     int updateUserSession(Long id, T t);
     int deleteUserSession(Long id);
}
