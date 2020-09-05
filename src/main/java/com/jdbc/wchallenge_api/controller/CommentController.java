package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Comment;
import com.jdbc.wchallenge_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping
  public List<Comment> findAll() {
    return commentService.findAll();
  }

  @GetMapping(params = "name")
  public List<Comment> findsByName(@RequestParam(required = false) String name) {
    if (name != null && !name.isEmpty())
      return commentService.findByName(name);
    else
      return Collections.emptyList();
  }

//  @GetMapping(params = "postId")
//  public List<Comment> findByPost(@RequestParam(required = false) String postId) {
//    if (postId != null && !postId.isEmpty())
//      return commentService.findByPost(Integer.parseInt(postId));
//    else
//      return Collections.emptyList();
//  }

  @GetMapping("/{id}")
  public Comment findById(@PathVariable int id) {
    return commentService.findById(id);
  }
}
