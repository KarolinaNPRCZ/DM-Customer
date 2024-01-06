package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.security;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserService;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    UserDetailsService userDetailsService(UserService userService, UserMapperInterfaceImpl userMapperInterfaceImpl) {
        return username -> userMapperInterfaceImpl.fromUserDTO(
                userService.getUserByUserEmail(username)
        );
    }


    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService, UserMapperInterfaceImpl userMapperInterfaceImpl) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService(userService,userMapperInterfaceImpl));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuditorAware<Integer> auditorAware() {
        return new ApplicationAuditAware();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public UserMapperInterfaceImpl userMapperInterface() {
        return new UserMapperInterfaceImpl();
    }


}

