package com.example.DockerMongoExTcLombkWeb.error;

import java.util.List;

 record BadCredentialsResponseDTO(List<String> errors) {
}
