package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Post;
import com.jdbc.wchallenge_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

  @Autowired
  private PostService postService;

  @GetMapping
  public List<Post> findAll() {
    return postService.findAll();
  }

  @GetMapping("/{id}")
  public Post findById(@PathVariable String id) {
    return postService.findById(Integer.parseInt(id));
  }

  @GetMapping(params = {"userId"})
  public List<Post> findByUser(@RequestParam String userId) {
    if (userId != null && !userId.isEmpty())
      return postService.findByUser(Integer.parseInt(userId));
    else
      return Collections.emptyList();
  }
}
