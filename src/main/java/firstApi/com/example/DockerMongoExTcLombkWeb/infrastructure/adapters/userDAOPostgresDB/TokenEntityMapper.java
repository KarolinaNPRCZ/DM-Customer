package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.TokenDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Log4j2
@Service
 class TokenEntityMapper implements Function<TokenDTO,Token> {
 private  UserEntityMapper userEntityMapper;



    @Override
    public Token apply(TokenDTO tokenDTO) {
        return new Token(
                tokenDTO.id(),
                tokenDTO.userJWTTokenChain(),
                tokenDTO.userTokenType(),
                tokenDTO.revoked(), tokenDTO.expired(),
                userEntityMapper.mapToUser(tokenDTO.userDTO())
        );
    }
    Token mapToToken(TokenDTO tokenDTO) {
        return apply(tokenDTO);
    }
}
