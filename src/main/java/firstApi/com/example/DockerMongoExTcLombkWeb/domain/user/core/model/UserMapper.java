package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model;

import firstApi.com.example.DockerMongoExTcLombkWeb.GenericConverter;

public  class UserMapper {


    public static User createFromDTO(UserDTO dto) {
        return updateEntity(dto);
    }

    public static User updateEntity(UserDTO dto) {

        return new User(dto.id(), dto.name(), dto.password());
    }
}
