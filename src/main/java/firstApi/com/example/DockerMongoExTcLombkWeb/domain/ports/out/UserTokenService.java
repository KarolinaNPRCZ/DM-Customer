package firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.TokenDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserTokenService {
    Optional<TokenDTO> getValidTokenByTokenId(Integer id);

    List<TokenDTO> getAllByIdAndExpiredIsFalseAndRevokedIsFalse(Integer id);

    Optional<TokenDTO> findTokenByUserJWTTokenChain(String userJWTTokenChain);

    void saveUserToken(UserDTO userDTO, String token);

    void saveUserToken(TokenDTO tokenDTO);
}
