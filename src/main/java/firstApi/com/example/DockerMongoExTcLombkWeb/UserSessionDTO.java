package firstApi.com.example.DockerMongoExTcLombkWeb;
import lombok.Builder;
@Builder(toBuilder = true)
public record UserSessionDTO(Long id, String username, String password){}





