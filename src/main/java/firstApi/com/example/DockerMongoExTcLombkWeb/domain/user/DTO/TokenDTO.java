package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO;

import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB.TokenType;
import lombok.Builder;

@Builder(toBuilder = true)
public record TokenDTO(Integer id, String userJWTTokenChain, TokenType userTokenType, boolean revoked, boolean expired,
                       UserDTO userDTO) {
}
