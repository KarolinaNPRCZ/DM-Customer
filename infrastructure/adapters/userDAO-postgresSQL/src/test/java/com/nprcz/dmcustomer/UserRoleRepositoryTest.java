package com.nprcz.dmcustomer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ContextConfiguration(classes = UserRoleRepositoryTestConfig.class)
class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    void save_and_get_userRole() {
        //GIVEN
        UserRole userRole = new UserRole(1, Role.USER, null);
        //WHEN
        userRoleRepository.save(userRole);
        UserRole getRole = userRoleRepository.getUserRoleByName(Role.USER);
        //THEN
        assertThat(userRole.getName()).isEqualTo(getRole.getName());
    }


}