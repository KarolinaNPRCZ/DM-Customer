package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.application.error;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.UserEmailArledyExistsException;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserControllerExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    UserAlreadyExistsResponse handle(UserEmailArledyExistsException exception) {

        return new UserAlreadyExistsResponse(
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }
    /*@LogHandlerMethodExec(
            value = "UserRestControllerErrorHandler",
            handlerClazz = UserControllerExceptionHandler.class,
            caughtException = UserEmailArledyExistsException.class
    )*/

  /*  @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }*/
}
