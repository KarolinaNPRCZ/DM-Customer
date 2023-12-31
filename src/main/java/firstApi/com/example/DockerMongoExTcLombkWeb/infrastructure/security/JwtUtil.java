package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.security;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserService;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.authcontroller.LoginRequest;
import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.authcontroller.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class JwtUtil {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    public JwtUtil(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.userEmail(),
                        loginRequest.userPassword()
                )
        );
        UserDTO userDTO = userService.getUserByUserEmail(loginRequest.userEmail());
        var jwtToken = jwtService.generateToken(userDTO);
        var refreshToken = jwtService.generateRefreshToken(userDTO);
        userDTO.toBuilder().Token(refreshToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

}
