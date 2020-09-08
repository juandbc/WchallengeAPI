package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Post;
import com.jdbc.wchallenge_api.repository.db.PostDbRepository;
import com.jdbc.wchallenge_api.repository.web.PostWebRepository;
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
public class PostService {

  private final PostDbRepository postDbRepository;
  private final PostWebRepository postRepository;

  @Autowired
  public PostService(PostDbRepository postDbRepository, PostWebRepository postRepository) {
    this.postDbRepository = postDbRepository;
    this.postRepository = postRepository;
  }

  public List<Post> findAll() {
    List<Post> posts = new ArrayList<>();

    posts.addAll(postRepository.findAll());
    posts.addAll(postDbRepository.findAll());

    return posts;
  }

  public Post findById(int id) {
    Optional<Post> p = postDbRepository.findById(id);

    return p.orElse(postRepository.findById(id));
  }

  public List<Post> findByUser(int userId) {
    return postRepository.findByUser(userId);
  }

  public void insert(Post post) {
    postDbRepository.insert(post);
  }

  public void update(Post post) {
    postDbRepository.save(post);
  }
}
