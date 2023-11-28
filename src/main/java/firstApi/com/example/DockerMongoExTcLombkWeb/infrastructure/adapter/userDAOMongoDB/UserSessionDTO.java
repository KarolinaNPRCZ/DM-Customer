package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapter.userDAOMongoDB;
import lombok.Builder;
@Builder(toBuilder = true)
public record UserSessionDTO(Long id, String username, String password){}





