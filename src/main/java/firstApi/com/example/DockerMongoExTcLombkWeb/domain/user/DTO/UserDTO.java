package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO;
import lombok.Builder;

import java.util.List;


@Builder(toBuilder = true)
public record UserDTO(String email, String password, List<UserRoleDTO> roles){}




