package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Post;
import com.jdbc.wchallenge_api.repository.PostWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class PostService {

  private final PostWebRepository postRepository;

  @Autowired
  public PostService(PostWebRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> findAll() {
    return postRepository.findAll();
  }

  public Post findById(int id) {
    return postRepository.findById(id);
  }

  public List<Post> findByUser(int userId) {
    return postRepository.findByUser(userId);
  }
}
