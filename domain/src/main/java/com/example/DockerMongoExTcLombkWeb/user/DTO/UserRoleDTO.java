package com.example.DockerMongoExTcLombkWeb.user.DTO;

import lombok.Builder;

;

@Builder(toBuilder = true)
public record UserRoleDTO(String name) {

}
