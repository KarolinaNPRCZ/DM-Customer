package com.example.DockerMongoExTcLombkWeb.error;

import java.util.List;

 public record BadCredentialsResponseDTO(List<String> errors) {
}
