package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model;

import firstApi.com.example.DockerMongoExTcLombkWeb.GenericConverter;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Log4j2
public  class UserMapper {


    public static User createFromDTO(UserDTO dto) {
        return updateEntity(dto);
    }

    public static User updateEntity(UserDTO dto) {
        log.info("to ja mapper");
        return new User( dto.email(), dto.password());
    }
}
