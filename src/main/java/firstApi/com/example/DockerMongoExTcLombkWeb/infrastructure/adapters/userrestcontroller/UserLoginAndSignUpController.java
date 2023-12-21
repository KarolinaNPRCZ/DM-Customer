package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userrestcontroller;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserLoginAndSignUpControllerPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserLoginAndSignUpController implements UserLoginAndSignUpControllerPort<ResponseEntity<?>,UserRegisterRequest> {
    private final UserService userService;


    public UserLoginAndSignUpController(UserService userService) {
        this.userService = userService;
    }

    //TODO password encoder,handle validation,UserRegisterReQ to DTO in other class facade?
    @PostMapping()
    @Override
    public ResponseEntity<UserId> createUser(@Valid @RequestBody UserRegisterRequest UserRegisterRequest) {
        log.info("to ja controller");
        UserDTO userDTO = new UserDTO(UserRegisterRequest.getUserEmail(), UserRegisterRequest.getUserPassword());

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

