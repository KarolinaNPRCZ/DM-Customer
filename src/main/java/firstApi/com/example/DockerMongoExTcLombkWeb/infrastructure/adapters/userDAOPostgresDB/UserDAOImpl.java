package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.UserEmailArledyExistsException;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.DTO.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.ports.in.UserDAOPort;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.UserMapperInterface;
import firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.security.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.Optional;


@Log4j2
 class UserDAOImpl implements UserDAOPort {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final UserEntityMapper userEntityMapper;

    private final UserRoleRepository userRoleRepository;


    public UserDAOImpl(UserRepository userRepository, UserDTOMapper userDTOMapper, UserEntityMapper userEntityMapper, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.userEntityMapper = userEntityMapper;
        this.userRoleRepository = userRoleRepository;

    }


    @Override
    public Optional<UserDTO> getUserByUserEmail(String userEmail) {
        log.info("DAOImpl download data");
        Optional<UserDTO> userDTO = userRepository.getUserByUserEmail(userEmail).map(userDTOMapper::mapToDTO);

        return userDTO;
    }

    @Override
    public UserId save(UserDTO userDTO) {
        User user = userEntityMapper.mapToUser(userDTO);
        user.setRoles(Collections.singletonList(userRoleRepository.getUserRoleByName(Role.USER)));
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

