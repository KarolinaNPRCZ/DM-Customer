package firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.out;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.authcontroller.LoginRequest;
import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.authcontroller.LoginResponse;

public interface UserService {
    UserId createUser(String userEmail,String hashedPassword);

    UserDTO getUserByUserEmail(String email);

}
