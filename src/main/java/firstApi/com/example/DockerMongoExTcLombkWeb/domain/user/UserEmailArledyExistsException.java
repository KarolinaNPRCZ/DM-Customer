package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user;

public class UserEmailArledyExistsException extends RuntimeException {
    public UserEmailArledyExistsException(String format) {
    super(format);
    }
}
