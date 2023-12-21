package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User with email: %s not found";

    public UserNotFoundException(String email) {
        super(MESSAGE.formatted(email));
    }
}
