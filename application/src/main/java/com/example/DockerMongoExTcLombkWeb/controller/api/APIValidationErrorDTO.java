package com.example.DockerMongoExTcLombkWeb.controller.api;

import java.util.List;

public record APIValidationErrorDTO(List<FieldErrorMessages> errors) {
}
