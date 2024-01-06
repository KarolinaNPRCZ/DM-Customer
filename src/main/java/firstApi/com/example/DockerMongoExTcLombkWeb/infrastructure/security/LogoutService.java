package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.security;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out.UserTokenService;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.TokenDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final UserTokenService userTokenService;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        Optional<TokenDTO> storedToken = userTokenService.findTokenByUserJWTTokenChain(jwt);
        if (storedToken.isPresent()) {
            storedToken.get().toBuilder().expired(true);
            storedToken.get().toBuilder().revoked(true);
            userTokenService.saveUserToken(storedToken.get());
            SecurityContextHolder.clearContext();
        }
    }
}

