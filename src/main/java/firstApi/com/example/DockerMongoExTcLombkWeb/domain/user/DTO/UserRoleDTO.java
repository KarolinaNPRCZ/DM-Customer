package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO;

import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB.Role;
import lombok.Builder;

@Builder(toBuilder = true)
public record UserRoleDTO(Role name) {

}
