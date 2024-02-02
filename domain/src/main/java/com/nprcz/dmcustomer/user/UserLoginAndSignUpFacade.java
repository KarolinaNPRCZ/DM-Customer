package com.nprcz.dmcustomer.user;

import com.nprcz.dmcustomer.ports.in.UserDAOPort;
import com.nprcz.dmcustomer.ports.out.UserService;
import com.nprcz.dmcustomer.user.DTO.UserDTO;


public class UserLoginAndSignUpFacade implements UserService {

    private final UserDAOPort userDAOPort;


    public UserLoginAndSignUpFacade(UserDAOPort userDAOPort) {
        this.userDAOPort = userDAOPort;

    }

    @Override
    public Integer createUser(String userEmail, String password) throws UserAlreadyExistsException {
        UserDTO userDTO = UserDTO.builder().email(userEmail).password(password).build();


        return userDAOPort.save(userDTO);

    }


    @Override
    public UserDTO getUserDTOByUserEmail(String userEmail) throws UserNotFoundException {
        return userDAOPort.getUserDTOByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));
    }


}

