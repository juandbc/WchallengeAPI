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
class AlbumControllerTest {

  @LocalServerPort
  private int port;

  private WebTestClient webTestClient;
  private static final String PATH = "/api/v1/albums/";

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:" + port + PATH;
    webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl).build();
  }

  @Test
  void findAllUsers() {
    webTestClient.get().accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[0].title").isEqualTo("quidem molestiae enim");
  }

  @Test
  void findUserById() {
    webTestClient.get().uri("9").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.title").isEqualTo("saepe unde necessitatibus rem");
  }

  @Test
  void albumNotFoundById() {
    webTestClient.get().uri("0").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody().json("{}");
  }

  @Test
  void getBadRequestWhenFindAlbumWithBadId() {
    webTestClient.get().uri("s").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest();
  }
}
