package com.example.DockerMongoExTcLombkWeb.user.DTO;

import lombok.Builder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


;

@Builder(toBuilder = true)
public record UserRoleDTO(String name) {
    public SimpleGrantedAuthority getAuthorities() {
        return new SimpleGrantedAuthority(name);
    }

}
