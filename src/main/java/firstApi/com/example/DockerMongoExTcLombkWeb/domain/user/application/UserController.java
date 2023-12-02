package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.application;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserControllerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

        private final UserControllerPort controllerPort;

    public UserController(UserControllerPort controllerPort) {
        this.controllerPort = controllerPort;
    }

    @GetMapping
        public List<User> getAllUsers() {
            return null;
        }

        @GetMapping("/{id}")
        public User getUserById(@PathVariable Long id) {
            return null;
        }

        @PostMapping("")
        public ResponseEntity<String> createUserDTO(@RequestBody UserDTO userDTO) {
            controllerPort.createUser(userDTO);
            return new ResponseEntity<>("New user was added to system", HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public User updateUser(@PathVariable Long id, @RequestBody User user) {

            return null;
        }


    }

