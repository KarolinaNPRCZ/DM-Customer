package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.authcontroller;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder(toBuilder = true)
public record LoginResponse(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken) {

}
