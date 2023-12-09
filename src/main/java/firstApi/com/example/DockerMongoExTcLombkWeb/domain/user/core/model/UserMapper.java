package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model;

import firstApi.com.example.DockerMongoExTcLombkWeb.GenericConverter;

public  class UserMapper {


    public static User createFromDTO(UserDTO dto) {
        return updateEntity(dto);
    }

    public static User updateEntity(UserDTO dto) {
        System.out.println("to ja user mapper");
        return new User( dto.name(), dto.password());//dto.id(),
    }
}
