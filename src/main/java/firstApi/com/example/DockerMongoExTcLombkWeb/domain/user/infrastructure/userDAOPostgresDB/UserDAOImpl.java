package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB;

import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.UserEmailArledyExistsException;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserDTO;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model.UserId;
import firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.ports.in.UserDAOPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;


@Log4j2
 class UserDAOImpl implements UserDAOPort {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final UserMapper userMapper;

    public UserDAOImpl(UserRepository userRepository, UserDTOMapper userDTOMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.userMapper = userMapper;
    }


    @Override
    public UserDTO getUserByUserEmail(String userEmail) {
        log.info("DAOImpl download data");
        return userDTOMapper.mapToDTO(userRepository.getUserByUserEmail(userEmail));
    }

    @Override
    public UserId save(UserDTO userDTO) {
        User user = userMapper.mapToUser(userDTO);
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

