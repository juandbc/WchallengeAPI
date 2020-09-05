package com.jdbc.wchallenge_api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
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

  @Value("${postsPath}")
  private String path;

  private WebTestClient webTestClient;

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:" + port + "/api/v1";
    webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl + path)
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
  void findById() {
    webTestClient.get().uri("/2").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo("2")
            .jsonPath("$.title").isEqualTo("qui est esse");
  }
}
