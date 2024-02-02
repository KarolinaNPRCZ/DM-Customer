package com.nprcz.dmcustomer.user.DTO;

import lombok.Builder;

import java.util.List;


@Builder(toBuilder = true)
public record UserDTO(Integer userId, String email, String password, List<UserRoleDTO> roles, String Token) {

}




