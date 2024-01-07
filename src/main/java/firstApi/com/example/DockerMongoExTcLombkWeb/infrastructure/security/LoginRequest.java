package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.security;


import lombok.Builder;

@Builder(toBuilder = true)
public record LoginRequest(String userEmail,
                           String userPassword) {

}
