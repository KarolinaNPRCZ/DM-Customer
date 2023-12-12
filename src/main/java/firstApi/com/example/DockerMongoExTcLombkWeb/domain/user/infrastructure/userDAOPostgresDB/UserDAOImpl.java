package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.User;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserMapper;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.GetUserByEmailDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.UserRepository;
import lombok.extern.log4j.Log4j2;

import static firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserMapper.createFromDTO;
import static firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserMapper.createFromEntity;

@Log4j2
public class UserDAOImpl implements UserDAOPort {

    private final UserRepository userRepository;


    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findUserByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        // Create and return the Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .roles("ADMIN") // Replace with appropriate roles from userDetails if available
                .build();
    }*/


    @Override
    public UserDTO getUserByEmail(String email) {
        log.info("to ja daoimpl pobieram dane");
        User user = userRepository.getUserByEmail(email);
        UserDTO userDTO = createFromEntity(user);
        return userDTO;
    }

    @Override
    public UserId save(UserDTO userDTO) {
        log.info("to ja dao impl zapisuje");

        User user = createFromDTO(userDTO);
        User savedUser = userRepository.save(user);
        return new UserId(savedUser.getId());
    }
}

