package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Post;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public interface PostRepository {

  List<Post> findAll();

  Post findById(int id);

  List<Post> findByUser(int userId);
}
