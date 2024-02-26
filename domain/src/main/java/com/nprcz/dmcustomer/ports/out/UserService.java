package com.nprcz.dmcustomer.ports.out;
import com.nprcz.dmcustomer.user.DTO.UserDTO;

public interface UserService {
    Integer createUser(String userEmail, String hashedPassword);

    UserDTO getUserDTOByUserEmail(String email);

}
