package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.User;
import com.jdbc.wchallenge_api.repository.UserWebRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.properties")
@ContextConfiguration(classes = {UserService.class, UserWebRepository.class})
class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  void findAllUsers() {
    List<User> emptyList = new ArrayList<>();
    List<User> actualUsers = userService.findAll();

    assertThat(actualUsers).isNotNull().isNotEqualTo(emptyList);
  }

  @Test
  void findUserById() {
    User expectedUser = new User();
    expectedUser.setId(3);
    expectedUser.setName("Clementine Bauch");
    expectedUser.setUsername("Samantha");

    User actualUser = userService.findById(3);

    assertThat(actualUser).isNotNull().isEqualToComparingOnlyGivenFields(expectedUser
            , "name", "username", "id");
  }
}
