package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Post;
import com.jdbc.wchallenge_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

  private final PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public List<Post> findAll() {
    return postService.findAll();
  }

  @GetMapping("/{id}")
  public Post findById(@PathVariable int id) {
    return postService.findById(id);
  }

  @GetMapping(params = {"userId"})
  public List<Post> findByUser(@RequestParam int userId) {
    return postService.findByUser(userId);
  }
}
