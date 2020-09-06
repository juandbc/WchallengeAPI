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
class CommentControllerTest {

  @LocalServerPort
  private int port;

  private WebTestClient webTestClient;

  private static final String PATH = "/api/v1/comments/";

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:" + port + PATH;
    webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl).build();
  }

  @Test
  void findAllComments() {
    webTestClient.get().accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$[*].name").exists()
            .jsonPath("$[*].body").exists()
            .jsonPath("$[*].email").exists();
  }

  @Test
  void findCommentById() {
    webTestClient.get().uri("5").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.postId").isEqualTo(1)
            .jsonPath("$.email").isEqualTo("Hayden@althea.biz");
  }

  @Test
  void findCommentByName() {
    webTestClient.get().uri("?name=id labore ex et quam laborum").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$[0].postId").isEqualTo(1)
            .jsonPath("$[0].email").isEqualTo("Eliseo@gardner.biz");
  }

  @Test
  void commentNotFoundById() {
    webTestClient.get().uri("0").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody().json("{}");
  }

  @Test
  void commentNotFoundByName() {
    webTestClient.get().uri("?name=hola").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody().json("[]");
  }

  @Test
  void getBadRequestBadId() {
    webTestClient.get().uri("s").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectStatus().isBadRequest();
  }
}
