package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.user.DTO.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log4j2
@Service
 class UserEntityMapper implements Function<UserDTO,User> {


    @Override
    public User apply(UserDTO userDTO) {
        return new User(

                userDTO.email(),
                userDTO.password()
        );
    }
    User mapToUser(UserDTO userDTO) {
        return apply(userDTO);
    }
}
