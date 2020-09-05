package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.User;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public interface UserRepository {

  List<User> findAll();
  User findById(int id);
}
