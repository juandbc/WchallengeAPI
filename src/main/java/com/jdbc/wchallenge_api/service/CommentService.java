package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Comment;
import com.jdbc.wchallenge_api.model.Post;
import com.jdbc.wchallenge_api.repository.db.CommentDbRepository;
import com.jdbc.wchallenge_api.repository.web.CommentWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class CommentService {

  private final CommentDbRepository commentDbRepository;
  private final CommentWebRepository commentRepository;
  private final PostService postService;

  @Autowired
  public CommentService(CommentDbRepository commentDbRepository, CommentWebRepository commentRepository, PostService postService) {
    this.commentDbRepository = commentDbRepository;
    this.commentRepository = commentRepository;
    this.postService = postService;
  }

  public List<Comment> findAll() {
    List<Comment> comments = new ArrayList<>();

    comments.addAll(commentRepository.findAll());
    comments.addAll(commentDbRepository.findAll());

    return comments;
  }

  public Comment findById(int id) {
    Optional<Comment> comment = commentDbRepository.findById(id);

    return comment.orElse(commentRepository.findById(id));
  }

  public List<Comment> findByName(String name) {
    List<Comment> comments = new ArrayList<>();

    comments.addAll(commentRepository.findByName(name));
    comments.addAll(commentDbRepository.findAll());

    return comments;
  }

  public List<Comment> findCommentsByUser(int id) {
    List<Comment> userComments = new ArrayList<>();
    List<Post> userPosts = postService.findByUser(id);

    userPosts.forEach(post ->
            userComments.addAll(commentRepository.findAll()
                    .stream()
                    .filter(comment -> comment.getPostId() == post.getId())
                    .collect(Collectors.toList()))
    );

    return userComments;
  }

  public void insert(Comment comment) {
    commentDbRepository.insert(comment);
  }

  public void update(Comment comment) {
    commentDbRepository.save(comment);
  }
}
