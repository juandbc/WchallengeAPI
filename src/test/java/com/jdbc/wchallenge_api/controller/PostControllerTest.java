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
class PostControllerTest {

  @LocalServerPort
  private int port;

  private WebTestClient webTestClient;

  private static final String PATH = "/api/v1/posts/";

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:" + port + PATH;
    webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl)
            .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                    .maxInMemorySize(10 * 1024 * 1024))
            .build();
  }

  @Test
  void findAllPosts() {
    webTestClient.get().accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectStatus().isOk()
            .expectBody().jsonPath("$[0].id").exists()
            .jsonPath("$[0].title").hasJsonPath();
  }

  @Test
  void findUserPosts() {
    webTestClient.get().uri("?userId=4").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[0].id").isEqualTo(31);
  }

  @Test
  void findById() {
    webTestClient.get().uri("2").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo("2")
            .jsonPath("$.title").isEqualTo("qui est esse");
  }

  @Test
  void postNotFound() {
    webTestClient.get().uri("0").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
  }

  @Test
  void postsNotFoundByUser() {
    webTestClient.get().uri("?userId=0").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody().json("[]");
  }

  @Test
  void getBadRequestWithBadId() {
    webTestClient.get().uri("h").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest();
  }

  @Test
  void getBadRequestWithBadUserId() {
    webTestClient.get().uri("?userId=g").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest();
  }
}
