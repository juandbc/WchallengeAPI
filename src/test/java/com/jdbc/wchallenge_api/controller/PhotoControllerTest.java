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

  private static final String PATH = "/api/v1/photos/";

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:" + port + PATH;
    webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl)
            .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                    .maxInMemorySize(10 * 1024 * 1024))
            .build();
  }

  @Test
  void findAllPhotos() {
    webTestClient.get().accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[6].title").isEqualTo("officia delectus consequatur vero aut veniam explicabo molestias");
  }

  @Test
  void findPhotoById() {
    webTestClient.get().uri("15").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo(15)
            .jsonPath("$.thumbnailUrl").isEqualTo("https://via.placeholder.com/150/f9cee5");
  }

  @Test
  void photoNotFound() {
    webTestClient.get().uri("0").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectStatus().isOk()
            .expectBody().json("{}");
  }

  @Test
  void getBadRequestWithBadId() {
    webTestClient.get().uri("s").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest();
  }
}
