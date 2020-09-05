package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Post;
import com.jdbc.wchallenge_api.repository.PostWebRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.properties")
@ContextConfiguration(classes = {PostService.class, PostWebRepository.class})
class PostServiceTest {

  @Autowired
  private PostService postService;
  private static Post expectedPost;

  @BeforeAll
  static void setPost() {
    expectedPost = new Post();
    expectedPost.setId(100);
    expectedPost.setTitle("at nam consequatur ea labore ea harum");
    expectedPost.setUserId(10);
    expectedPost.setBody("cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut");
  }

  @Test
  void findAllPosts() {
    List<Post> emptyList = new ArrayList<>();
    List<Post> actualPosts = postService.findAll();

    assertThat(actualPosts).isNotNull().isNotEqualTo(emptyList);
  }

  @Test
  void findByUser() {
    List<Post> emptyList = new ArrayList<>();
    List<Post> actualPosts = postService.findByUser(10);

    assertThat(actualPosts).isNotNull()
            .isNotEqualTo(emptyList)
            .contains(expectedPost);
  }

  @Test
  void findById() {
    Post actualPost = postService.findById(100);

    assertThat(actualPost).isNotNull().isEqualTo(expectedPost);
  }
}
