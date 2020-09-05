package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.User;
import com.jdbc.wchallenge_api.repository.UserRepository;
import com.jdbc.wchallenge_api.repository.UserWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserWebRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(int id) {
    return userRepository.findById(id);
  }
}
