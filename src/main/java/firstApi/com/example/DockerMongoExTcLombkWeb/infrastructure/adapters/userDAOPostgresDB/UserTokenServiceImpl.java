package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserTokenService;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.TokenDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class UserTokenServiceImpl implements UserTokenService {
    private final TokenRepository tokenRepository;
    private final TokenDTOMapper tokenDTOMapper;
    private final TokenEntityMapper tokenEntityMapper;
    private final UserEntityMapper userEntityMapper;

    UserTokenServiceImpl(TokenRepository tokenRepository, TokenDTOMapper tokenDTOMapper, TokenEntityMapper tokenEntityMapper, UserEntityMapper userEntityMapper) {
        this.tokenRepository = tokenRepository;
        this.tokenDTOMapper = tokenDTOMapper;

        this.tokenEntityMapper = tokenEntityMapper;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public Optional<TokenDTO> getValidTokenByTokenId(Integer id) {
        return tokenRepository.getValidTokenByTokenId(id).map(tokenDTOMapper::mapToDTO);
    }

    @Override
    public List<TokenDTO> getAllByIdAndExpiredIsFalseAndRevokedIsFalse(Integer id) {
        return tokenRepository.getAllByIdAndExpiredIsFalseAndRevokedIsFalse(id).stream().map(tokenDTOMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TokenDTO> findTokenByUserJWTTokenChain(String userJWTTokenChain) {
        return tokenRepository.findTokenByUserJWTTokenChain(userJWTTokenChain).map(tokenDTOMapper::mapToDTO);
    }

    @Override
    public void saveUserToken(UserDTO userDTO, String jwtToken) {
            var token = Token.builder()

                    .userJWTTokenChain(jwtToken)
                    .userTokenType(TokenType.BEARER)
                    .expired(false)
                    .revoked(false)
                    .build();
           // token.setUser(userEntityMapper.mapToUser(userDTO));
            tokenRepository.save(token);
        }

    @Override
    public void saveUserToken(TokenDTO tokenDTO) {
        tokenRepository.save(tokenEntityMapper.mapToToken(tokenDTO));
    }
}

