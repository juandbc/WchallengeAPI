package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Comment;
import com.jdbc.wchallenge_api.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.properties")
@ContextConfiguration(classes = {CommentService.class, CommentRepository.class})
class CommentServiceTest {

  @Autowired
  private CommentService commentService;

  @Test
  void findAllComments() {
    List<Comment> actualComments = commentService.findAll();
    assertThat(actualComments).isNotNull().hasSizeGreaterThan(100);
  }

  @Test
  void findCommentById() {
    Comment expectedComment = new Comment();
    expectedComment.setId(85);
    expectedComment.setPostId(17);
    expectedComment.setEmail("Alexzander_Davis@eduardo.name");

    Comment actualComment = commentService.findById(85);

    assertThat(actualComment).isNotNull().isEqualToIgnoringNullFields(expectedComment);
  }
}
