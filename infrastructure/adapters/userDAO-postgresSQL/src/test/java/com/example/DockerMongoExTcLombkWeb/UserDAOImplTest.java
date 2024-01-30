package com.example.DockerMongoExTcLombkWeb;

import com.example.DockerMongoExTcLombkWeb.user.DTO.UserDTO;
import com.example.DockerMongoExTcLombkWeb.user.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDAOImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDTOMapper userDTOMapper;
    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private UserEntityMapper userEntityMapper;
    @InjectMocks
    private UserDAOImpl userDAOImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_successfully_save_user_and_return_user_id() {
        // GIVEN
        UserDTO userDTO = new UserDTO(1, "username", "password", null, null);
        User user = new User();
        user.setId(1);
        when(userEntityMapper.mapToUser(userDTO))
                .thenReturn(user);
        when(userRepository.save(user))
                .thenReturn(user);

        // WHEN
        Integer userId = userDAOImpl.save(userDTO);

        // THEN
        assertEquals(1, userId);
        verify(userEntityMapper, times(1))
                .mapToUser(userDTO);
        verify(userRepository, times(1))
                .save(user);
    }

    @Test
    void should_throw_UserAlreadyExistsException() {
        // GIVEN
        UserDTO userDTO = new UserDTO(1, "username", "password", null, null);
        User userEntity = new User();
        when(userEntityMapper.mapToUser(userDTO))
                .thenReturn(userEntity);
        when(userRepository.save(userEntity))
                .thenThrow(DataIntegrityViolationException.class);

        // WHEN && THEN
        assertThrows(
                UserAlreadyExistsException.class,
                () -> userDAOImpl.save(userDTO)
        );
        verify(userEntityMapper, times(1))
                .mapToUser(userDTO);
        verify(userRepository, times(1))
                .save(userEntity);
    }

    @Test
    void should_successfully_find_user_by_getUserDTOByUserEmail() {
        String username = "test@test.com";
        //GIVEN
        UserDTO userDTO = new UserDTO(1, username, "Pasword", new ArrayList<>(), null);
        User user = new User();
        when(userRepository.getUserByUserEmail(username))
                .thenReturn(Optional.of(user));
        when(userDTOMapper.mapToDTO(user))
                .thenReturn(userDTO);
        // WHEN
        Optional<UserDTO> result = userDAOImpl.getUserDTOByUserEmail(username);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(userDTO, result.get());
        verify(userRepository, times(1)).getUserByUserEmail(username);


    }

    @Test
    public void getUserDTOByUserEmail_UserNotExists_ReturnsEmptyOptional() {
        // GIVEN
        String username = "notexistuser";
        when(userRepository.getUserByUserEmail(username))
                .thenReturn(Optional.empty());

        // WHEN
        Optional<UserDTO> result = userDAOImpl.getUserDTOByUserEmail(username);

        // THEN
        assertFalse(result.isPresent());
        verify(userRepository, times(1))
                .getUserByUserEmail(username);
        verify(userDTOMapper, never()).mapToDTO(any());
    }
}