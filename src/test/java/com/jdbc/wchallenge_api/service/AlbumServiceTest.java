package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.repository.AlbumWebRepository;
import org.junit.jupiter.api.BeforeEach;
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
@ContextConfiguration(classes = {AlbumService.class, AlbumWebRepository.class})
class AlbumServiceTest {

  @Autowired
  private AlbumService albumService;

  @Test
  void findAllAlbums() {
    List<Album> emptyList = new ArrayList<>();
    List<Album> actualAlbums = albumService.findAll();

    assertThat(actualAlbums).isNotNull().isNotEqualTo(emptyList);
  }

  @Test
  void findAlbumById() {
    Album expectedAlbum = new Album();
    expectedAlbum.setId(3);
    expectedAlbum.setTitle("omnis laborum odio");
    expectedAlbum.setUserId(1);

    Album actualAlbum = albumService.findById(3);

    assertThat(actualAlbum).isNotNull()
            .isEqualToComparingOnlyGivenFields(expectedAlbum, "userId", "title", "id");
  }
}
