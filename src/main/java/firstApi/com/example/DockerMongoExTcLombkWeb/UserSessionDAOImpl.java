package firstApi.com.example.DockerMongoExTcLombkWeb;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.UserSession;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserSessionDAOImpl implements UserSessionDAO {
    @Autowired
   UserSessionRepository userSessionRepository;
    @Override
    public List<UserSession> getAllUserSessions() {
        return null;
    }

    @Override
    public Optional<UserSession> getUserSessionById(Long id) throws SQLException {
        Optional<UserSession> userSession = userSessionRepository.getUserSessionById(id);

        return userSession;
    }

    @Override
    public int createUserSession(UserSession user) {
        return 0;
    }

    @Override
    public int updateUserSession(Long id, UserSession user) {
        return 0;
    }

    @Override
    public int deleteUserSession(Long id) {
        return 0;
    }
}
