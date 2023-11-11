package firstApi.com.example.DockerMongoExTcLombkWeb.application.rest;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.UserSession;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.repository.UserSessionRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserSessionControler {

        @Autowired
        private UserSessionRepository userSessionRepository;

        @GetMapping
        public List<UserSession> getAllUserSessions() {
            return userSessionRepository.findAll();
        }

        @GetMapping("/{id}")
        public UserSession getUserSessionById(@PathVariable Long id) {
            return userSessionRepository.findById(id).get();
        }

        @PostMapping
        public UserSession createUserSession(@RequestBody UserSession user) {
            return userSessionRepository.save(user);
        }

        @PutMapping("/{id}")
        public UserSession updateUserSession(@PathVariable Long id, @RequestBody UserSession user) {
            UserSession existingUserSession = userSessionRepository.findById(id).get();
            existingUserSession.setName(user.getName());
            existingUserSession.setPassword(user.getPassword());
            return userSessionRepository.save(existingUserSession);
        }

        @DeleteMapping("/{id}")
        public String deleteUserSession(@PathVariable Long id) {
            try {
                userSessionRepository.findById(id).get();
                userSessionRepository.deleteById(id);
                return "UserSession deleted successfully";
            } catch (Exception e) {
                return "UserSession not found";
            }
        }
    }

