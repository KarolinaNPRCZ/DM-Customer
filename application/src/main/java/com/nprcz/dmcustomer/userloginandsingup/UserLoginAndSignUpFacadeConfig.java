package com.nprcz.dmcustomer.userloginandsingup;


import com.nprcz.dmcustomer.ports.in.UserDAOPort;
import com.nprcz.dmcustomer.ports.out.UserService;
import com.nprcz.dmcustomer.user.UserLoginAndSignUpFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserLoginAndSignUpFacadeConfig {

    @Bean
    UserService UserControllerPort(UserDAOPort userDAOPort) {
        return new UserLoginAndSignUpFacade(userDAOPort);
    }


}
