package com.example.DockerMongoExTcLombkWeb.user;

import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;

import java.io.Serializable;
import java.util.function.Function;

public interface UserMapperInterface<T> extends Function<T, UserDTO>, Serializable {
    default UserDTO toUserDTO(T t){
        return apply(t);
    }
    T fromUserDTO(UserDTO userDTO);
}
