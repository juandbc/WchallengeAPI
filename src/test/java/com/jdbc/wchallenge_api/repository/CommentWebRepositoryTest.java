package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Comment;
import com.jdbc.wchallenge_api.repository.web.CommentWebRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = CommentWebRepository.class)
class CommentWebRepositoryTest {

  @Autowired
  private CommentWebRepository commentWebRepository;

  @Test
  void findAllUsers() {
    assertThat(this.commentWebRepository.findAll()).isNotNull()
            .isNotEmpty()
            .hasSizeGreaterThan(400);
  }

  @Test
  void findUserById() {
    Comment expectedComment = new Comment(14,
            "et officiis id praesentium hic aut ipsa dolorem repudiandae", "Nathan@solon.io",
            "vel quae voluptas qui exercitationem\nvoluptatibus unde sed\nminima et qui ipsam aspernatur\nexpedita magnam" +
                    " laudantium et et quaerat ut qui dolorum",
            3);
    assertThat(this.commentWebRepository.findById(14)).isNotNull()
            .isEqualTo(expectedComment);
  }
}
