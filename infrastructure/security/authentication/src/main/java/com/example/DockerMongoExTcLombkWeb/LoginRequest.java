package com.example.DockerMongoExTcLombkWeb;


import lombok.Builder;

@Builder(toBuilder = true)
public record LoginRequest(String userEmail,
                           String userPassword) {

}
