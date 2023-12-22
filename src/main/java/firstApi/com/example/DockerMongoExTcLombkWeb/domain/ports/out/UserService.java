package firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;

public interface UserService {
    UserId createUser(String userEmail,String hashedPassword);

    UserDTO getUserByUserEmail(String email);
}
