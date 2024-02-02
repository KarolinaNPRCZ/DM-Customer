package com.nprcz.dmcustomer;

import com.nprcz.dmcustomer.ports.in.UserDAOPort;
import com.nprcz.dmcustomer.user.DTO.UserDTO;
import com.nprcz.dmcustomer.user.UserAlreadyExistsException;
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
    public Optional<UserDTO> getUserDTOByUserEmail(String userEmail) {
        log.info("DAOImpl download user from DB");
        Optional<UserDTO> userDTO = userRepository.getUserByUserEmail(userEmail).map(userDTOMapper::mapToDTO);

        return userDTO;
    }

    @Override
    public Integer save(UserDTO userDTO) {
        User user = userEntityMapper.mapToUser(userDTO);
        user.setRoles(Collections.singletonList(userRoleRepository.getUserRoleByName(Role.USER)));
        User savedUser;
        log.info("DAOImpl try save user");
        try {
            savedUser = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new UserAlreadyExistsException("User with given e-mail already exists");
        }
        return savedUser.getId();
    }
}

