package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.security;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserService;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserTokenService;
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
    private final UserTokenService userTokenService;

    public JwtUtil(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService, UserTokenService userTokenService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userTokenService = userTokenService;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.userEmail(),
                        loginRequest.userPassword()
                )
        );
        var user = userService.getUserByUserEmail(loginRequest.userEmail());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        userTokenService.saveUserToken(user, jwtToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

}
