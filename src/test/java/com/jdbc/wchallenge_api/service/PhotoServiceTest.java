package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Photo;
import com.jdbc.wchallenge_api.repository.PhotoWebRepository;
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
@ContextConfiguration(classes = {PhotoService.class, PhotoWebRepository.class})
class PhotoServiceTest {

  @Autowired
  private PhotoService photoService;

  @Test
  void findAllPhotos() {
    List<Photo> emptyList = new ArrayList<>();
    List<Photo> actualPhotos = photoService.findAll();

    assertThat(actualPhotos).isNotNull()
            .isNotEqualTo(emptyList)
            .hasSizeGreaterThan(500);
  }

  @Test
  void findPhotoById() {
    Photo expectedPhoto = new Photo();
    expectedPhoto.setId(4996);
    expectedPhoto.setTitle("voluptatem ab aliquam dolorum vel voluptas qui repellendus");
    expectedPhoto.setUrl("https://via.placeholder.com/600/b3db9a");

    Photo actualPhoto = photoService.findById(4996);

    assertThat(actualPhoto).isNotNull().isEqualToComparingOnlyGivenFields(expectedPhoto
            , "title", "url", "id");
  }
}
