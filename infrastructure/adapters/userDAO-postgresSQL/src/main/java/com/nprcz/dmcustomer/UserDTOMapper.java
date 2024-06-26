package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.user.DTO.UserDTO;
import com.nprcz.dmcustomer.user.DTO.UserRoleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Service
class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return  UserDTO.builder()
                .userId(user.getId())
                .email(user.getUserEmail())
                .password(user.getUserPassword())
                .roles(getListOfUserRoles(user))
                .build();
    }

    UserDTO mapToDTO(User user) {
        return apply(user);
    }

    List<UserRoleDTO> getListOfUserRoles(User user) {
        return user
                .getRoles()
                .stream()
                .map(userRole -> new UserRoleDTO(userRole
                                .getName()
                                .name()))
                .collect(Collectors.toList()
                );
    }


}
