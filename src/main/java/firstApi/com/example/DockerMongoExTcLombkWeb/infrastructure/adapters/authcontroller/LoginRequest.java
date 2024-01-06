package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.authcontroller;


import lombok.Builder;

@Builder(toBuilder = true)
public record LoginRequest(String userEmail,
                           String userPassword) {

}
