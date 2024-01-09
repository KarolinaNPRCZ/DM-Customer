package com.example.DockerMongoExTcLombkWeb;

import com.example.DockerMongoExTcLombkWeb.ports.out.UserService;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserId;
import com.example.DockerMongoExTcLombkWeb.ports.in.UserLoginAndSignUpControllerPort;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
 class UserLoginAndSignUpController implements UserLoginAndSignUpControllerPort<ResponseEntity<?>,UserRegisterRequest> {
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



    @PreAuthorize("hasAuthority('ADMIN')")
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
}

