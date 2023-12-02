package firstApi.com.example.DockerMongoExTcLombkWeb;

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
