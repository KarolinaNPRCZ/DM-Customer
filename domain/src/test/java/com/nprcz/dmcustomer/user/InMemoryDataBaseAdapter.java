package com.nprcz.dmcustomer.user;

import com.nprcz.dmcustomer.ports.in.UserDAOPort;
import com.nprcz.dmcustomer.user.DTO.UserDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryDataBaseAdapter implements UserDAOPort {
    private final Map<String, UserDTO> users = new HashMap<>();
    @Override
    public Optional<UserDTO> getUserDTOByUserEmail(String email) {
        return users.values().stream().filter(user -> user.email().equals(email)).findFirst();
    }

    @Override
    public Integer save(UserDTO userDTO) throws UserAlreadyExistsException {
        if (users.containsKey(userDTO.email())) throw new UserAlreadyExistsException("User with given e-mail already exists");
        UserDTO userToSave = userDTO.toBuilder().build();
        if (userDTO.userId() == null){
            userToSave = userDTO.toBuilder().userId(5).build();
        }
        users.put(userDTO.email(),userToSave);
        return userToSave.userId();
    }
}
