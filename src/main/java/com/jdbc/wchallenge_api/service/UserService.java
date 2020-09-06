package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.User;
import com.jdbc.wchallenge_api.repository.db.UserDbRepository;
import com.jdbc.wchallenge_api.repository.web.UserWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class UserService {

  private final UserWebRepository userWebRepository;
  private final UserDbRepository userDbRepository;

  @Autowired
  public UserService(UserWebRepository userRepository, UserDbRepository userDbRepository) {
    this.userWebRepository = userRepository;
    this.userDbRepository = userDbRepository;
  }

  public List<User> findAll() {
    List<User> users = new ArrayList<>();
    users.addAll(userWebRepository.findAll());
    users.addAll(userDbRepository.findAll());
    return users;
  }

  public User findById(int id) {
    Optional<User> optionalUser = userDbRepository.findById(id);

    return optionalUser.orElse(userWebRepository.findById(id));
  }
}
