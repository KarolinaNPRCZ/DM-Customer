package com.nprcz.dmcustomer.ports.in;

import com.nprcz.dmcustomer.user.DTO.UserDTO;
import com.nprcz.dmcustomer.user.UserAlreadyExistsException;

import java.util.Optional;

public interface UserDAOPort {
    Optional<UserDTO> getUserDTOByUserEmail(String email);

    Integer save(UserDTO userDTO) throws UserAlreadyExistsException;
    //
}
