package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.User;
import com.jdbc.wchallenge_api.repository.web.UserWebRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = UserWebRepository.class)
class UserRepositoryTest {

  @Autowired
  private UserWebRepository userRepository;

  @Test
  void findAllUsers() {
    assertThat(this.userRepository.findAll()).isNotNull()
            .isNotEmpty()
            .hasSizeGreaterThan(5);
  }

  @Test
  void findUserById() {
    User user = new User();
    user.setId(5);
    user.setName("Chelsey Dietrich");
    user.setUsername("Kamren");

    assertThat(this.userRepository.findById(5)).isNotNull()
            .isEqualToIgnoringNullFields(user);
  }
}
