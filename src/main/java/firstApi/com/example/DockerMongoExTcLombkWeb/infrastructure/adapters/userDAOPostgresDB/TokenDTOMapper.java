package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.TokenDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log4j2
@Service
class TokenDTOMapper implements Function<Token, TokenDTO> {

private final UserDTOMapper userDTOMapper;

    TokenDTOMapper(UserDTOMapper userDTOMapper) {
        this.userDTOMapper = userDTOMapper;
    }


    @Override
    public TokenDTO apply(Token token) {
        return new TokenDTO(
                token.getId(),
                token.getUserJWTTokenChain(),
                token.getUserTokenType(),
                token.isRevoked(), token.isExpired(),
                userDTOMapper.mapToDTO(token.getUser())
        );
    }

    TokenDTO mapToDTO(Token token) {
        return apply(token);
    }

}
