package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userrestcontroller;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserLoginAndSignUpControllerPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserLoginAndSignUpController implements UserLoginAndSignUpControllerPort<ResponseEntity<?>,UserRegisterRequest> {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public UserLoginAndSignUpController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")

    @Override
    public ResponseEntity<UserId> createUser(@Valid @RequestBody UserRegisterRequest UserRegisterRequest) {
        log.info("Handle Request = UserLoginAndSignUpController");
        UserId register = userService.createUser(UserRegisterRequest.getUserEmail(), passwordEncoder.encode(UserRegisterRequest.getUserPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(register);

    }

    @GetMapping("/{userEmail}")
    @Override
    public ResponseEntity<UserDTO> getUserByUserEmail(@PathVariable String userEmail) {
        log.warn("Trying to find user with email: {}...", userEmail);
        UserDTO userDTO = userService.getUserDTOByUserEmail(userEmail);
        log.info("User has successfully find: {}", userDTO);
        return ResponseEntity.ok(
                userDTO
        );
    }
    @GetMapping()
    public String hello() {
        return "HI";
    }
}

