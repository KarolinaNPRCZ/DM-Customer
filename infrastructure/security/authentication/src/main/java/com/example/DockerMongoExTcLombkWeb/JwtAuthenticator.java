package com.example.DockerMongoExTcLombkWeb;

import com.example.DockerMongoExTcLombkWeb.ports.out.UserService;
import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
@Log4j2
 class JwtAuthenticator {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    public JwtAuthenticator(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

     LoginResponse authenticate(LoginRequest loginRequest)  {
     log.info("Trying to authenticate user");
        try {
         authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         loginRequest.userEmail(),
                         loginRequest.userPassword()
                 )
         );
     }catch (BadCredentialsException badCredentialsException){
       log.error(  "User has used wrong credentials to authenticate!! with email: {} password: {}",
               loginRequest.userEmail(),
               loginRequest.userPassword());
       throw new AuthenticationException("Wrong e-mail or Password for: " + loginRequest.userEmail()){};
     }
         log.info("User authenticate success");
        UserDTO userDTO = userService.getUserDTOByUserEmail(loginRequest.userEmail());
        var jwtToken = jwtService.generateToken(userDTO);
        var refreshToken = jwtService.generateRefreshToken(userDTO);
        userDTO.toBuilder().Token(refreshToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

}
