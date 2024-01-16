package com.example.DockerMongoExTcLombkWeb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest

@ContextConfiguration(classes = UserRepositoryTestConfig.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void should_successfully_save_user_and_then_find_user_by_username() {
        // GIVEN
        User userEntity = new User("testUsername", "testPassword");

        // WHEN
        userRepository.save(userEntity);
        Optional<User> optionalUserEntity = userRepository.getUserByUserEmail(userEntity.getUserEmail());

        // THEN
        assertThat(optionalUserEntity).isPresent();
        assertThat(optionalUserEntity.get()).isEqualTo(userEntity);
    }

    @Test
    void should_thrown_an_exception_while_trying_to_find_nonexistent_user() {
        // GIVEN && WHEN
        Optional<User> optionalUserEntity = userRepository.getUserByUserEmail("nonexistentUser");

        // THEN
        assertThat(optionalUserEntity).isEmpty();
    }

    @Test
    void should_throw_an_exception_while_trying_to_save_duplicated_username() {
        // GIVEN
        User firstUserEntity = new User("test@test.com", "testPassword");


        User secondUserEntity = new User("test@test.com", "testPassword");

        // WHEN
        userRepository.save(firstUserEntity);


        // THEN
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAndFlush(secondUserEntity));
    }

}