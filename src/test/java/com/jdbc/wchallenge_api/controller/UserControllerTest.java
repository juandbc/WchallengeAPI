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
class UserControllerTest {

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
  void findAllUsers() {
    webTestClient.get().uri("/users").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[0].name").isEqualTo("Leanne Graham")
            .jsonPath("$[0].company.bs").exists();
  }

  @Test
  void findUserById() {
    webTestClient.get().uri("/users/5").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo(5)
            .jsonPath("$.company.catchPhrase").isEqualTo("User-centric fault-tolerant solution");
  }

  @Test
  void findUserComments() {
    webTestClient.get().uri("/users/5/comments").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[2].id").isEqualTo(203)
            .jsonPath("$[2].postId").isEqualTo(41)
            .jsonPath("$[2].email").isEqualTo("Bonita@karl.biz");
  }

  @Test
  void findUserPhotos() {
    webTestClient.get().uri("/users/2/photos").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$[5].id").isEqualTo(506)
            .jsonPath("$[5].albumId").isEqualTo(11)
            .jsonPath("$[5].title").isEqualTo("maxime unde repudiandae similique reiciendis harum");
  }
}
