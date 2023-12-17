package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.application.error;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;

public record UserAlreadyExistsResponse(
        String description,
        HttpStatus httpStatus
) {

}
