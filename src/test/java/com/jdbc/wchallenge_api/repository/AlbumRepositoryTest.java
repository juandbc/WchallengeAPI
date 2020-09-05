package com.jdbc.wchallenge_api.repository;

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
@ContextConfiguration(classes = AlbumWebRepository.class)
class AlbumRepositoryTest {

  @Autowired
  private AlbumWebRepository albumWebRepository;

  @Test
  void findAllUsers() {
    assertThat(this.albumWebRepository.findAll()).isNotNull()
            .isNotEmpty()
            .hasSizeGreaterThan(50);
  }

  @Test
  void findUserById() {
    assertThat(this.albumWebRepository.findById(80)).isNotNull()
            .hasFieldOrProperty("title")
            .hasFieldOrProperty("userId");
  }
}
