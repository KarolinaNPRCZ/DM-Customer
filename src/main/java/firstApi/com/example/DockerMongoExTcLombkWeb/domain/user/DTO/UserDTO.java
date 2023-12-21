package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO;
import lombok.Builder;


@Builder(toBuilder = true)
public record UserDTO(String email, String password){}

//long id,



