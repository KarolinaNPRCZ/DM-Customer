package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.UserEmailArledyExistsException;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserDAOPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;


@Log4j2
 class UserDAOImpl implements UserDAOPort {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final UserMapper userMapper;
   // private final UserRoleRepository userRoleRepository;

    public UserDAOImpl(UserRepository userRepository, UserDTOMapper userDTOMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.userMapper = userMapper;
    }


    @Override
    public Optional<UserDTO> getUserByUserEmail(String userEmail) {
        log.info("DAOImpl download data");
        Optional<UserDTO> userDTO = userRepository.getUserByUserEmail(userEmail).map(userDTOMapper);
        return userDTO;
    }

    @Override
    public UserId save(UserDTO userDTO) {
        User user = userMapper.mapToUser(userDTO);
        //user.setRoles(Set.of(userRoleRepository.getUserRoleByName(Role.ROLE_USER)));
        User savedUser;
        log.info("DAOImpl saving");
         try {
             savedUser = userRepository.save(user);
         }catch (DataIntegrityViolationException exception){
           throw new UserEmailArledyExistsException("User with given email address already exists");
         }
        return new UserId(savedUser.getId());
    }
}

