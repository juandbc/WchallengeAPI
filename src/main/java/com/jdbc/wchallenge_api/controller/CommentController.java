package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Comment;
import com.jdbc.wchallenge_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping
  public List<Comment> findAll() {
    return commentService.findAll();
  }

  @GetMapping(params = "name")
  public List<Comment> findsByName(@RequestParam String name) {
    if (name != null && !name.isEmpty())
      return commentService.findByName(name);
    else
      return Collections.emptyList();
  }

  @GetMapping("/{id}")
  public Comment findById(@PathVariable int id) {
    return commentService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void insertComment(@RequestBody Comment comment) {
    commentService.insert(comment);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateComment(@PathVariable int id, @RequestBody Comment comment) {
    Comment u = commentService.findById(id);

    if (u.getId() != 0) {
      commentService.update(comment);
      return ResponseEntity.noContent().build();
    } else {
      commentService.insert(comment);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
  }
}
