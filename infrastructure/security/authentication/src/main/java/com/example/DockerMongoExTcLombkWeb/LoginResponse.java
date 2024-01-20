package com.example.DockerMongoExTcLombkWeb;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder(toBuilder = true)
public record LoginResponse(
        @JsonProperty("access_token")
        String userEmail,
        @JsonProperty("refresh_token")
        String accessToken) {

}
