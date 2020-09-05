package com.jdbc.wchallenge_api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhotoControllerTest {

  @LocalServerPort
  private int port;

  private WebTestClient webTestClient;

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:" + port + "/api/v1";
    webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl)
            .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                    .maxInMemorySize(10 * 1024 * 1024))
            .build();
  }

  @Test
  void findAllPhotos() {
    webTestClient.get().uri("/photos").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[6].title").isEqualTo("officia delectus consequatur vero aut veniam explicabo molestias");
  }

  @Test
  void findPhotoById() {
    webTestClient.get().uri("/photos/15").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo( 15)
            .jsonPath("$.thumbnailUrl").isEqualTo( "https://via.placeholder.com/150/f9cee5");
  }
}
