package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
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

    assertThat(actualUser).isNotNull().isEqualToIgnoringNullFields(expectedUser);
  }
}
