package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in;


import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;

//create new User
public interface GetUserByEmailControllerPort<T> {
    T getUserByEmail(String email);
}
