package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model;
import lombok.Builder;
@Builder(toBuilder = true)
public record UserDTO(long id, String name, String password){}





