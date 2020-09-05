package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Comment;
import com.jdbc.wchallenge_api.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  public Comment findById(int id) {
    return commentRepository.findById(id);
  }

  public List<Comment> findByName(String name) {
    return commentRepository.findByName(name);
  }

}
