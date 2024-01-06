package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO;

import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB.Role;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@Builder(toBuilder = true)
public record UserRoleDTO(Role name) {
    public SimpleGrantedAuthority getAuthorities() {
       return new SimpleGrantedAuthority(name.name());
    }

}
