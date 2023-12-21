package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userrestcontroller.error;

import org.springframework.http.HttpStatus;

public record UserAlreadyExistsResponse(
        String description,
        HttpStatus httpStatus
) {

}
