package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.application;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserControllerPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.out.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController implements UserControllerPort {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    @Override
    public ResponseEntity<UserId> createUser(@RequestBody UserDTO userDTO) {
        log.info("to ja controller");
        //   String passwordAfterEncode = passwordEncoder.encode(userDTO.password());
        //  userDTO.toBuilder().password(passwordAfterEncode);
        UserId register = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @GetMapping("/{userEmail}")
    @Override
    public ResponseEntity<UserDTO> getUserByUserEmail(@PathVariable String userEmail) {
        log.warn("Trying to find user with email: {}...", userEmail);
        UserDTO userDTO = userService.getUserByUserEmail(userEmail);
        log.info("User has successfully find: {}", userDTO);
        return ResponseEntity.ok(
                userDTO
        );
    }
}

