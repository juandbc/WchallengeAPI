package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.AlbumPermission;
import com.jdbc.wchallenge_api.model.User;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlbumPermissionControllerTest {

  @LocalServerPort
  private int port;

  private WebTestClient webTestClient;
  private static final String PATH = "/api/v1/albums/permissions";
  private static List<AlbumPermission> albumPermissions;

  @BeforeAll
  static void init() {
    albumPermissions = new ArrayList<>();
    albumPermissions.add(new AlbumPermission(1, 101, 11, true, true));
    albumPermissions.add(new AlbumPermission(2, 101, 12, false, true));
    albumPermissions.add(new AlbumPermission(3, 101, 13, true, true));
    albumPermissions.add(new AlbumPermission(4, 102, 13, true, true));
    albumPermissions.add(new AlbumPermission(5, 103, 14, false, false));
  }

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:" + port + PATH;
    webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl).build();
  }

  @RepeatedTest(5)
  @Order(1)
  void insertPermission(RepetitionInfo repetitionInfo) {
    Mono<AlbumPermission> mono = Mono.justOrEmpty(albumPermissions.get(repetitionInfo.getCurrentRepetition() - 1));
    webTestClient.post()
            .body(mono, AlbumPermission.class)
            .exchange()
            .expectStatus().isCreated();
  }

  @Test
  @Order(2)
  void updatePermission() {
    Mono<AlbumPermission> mono = Mono.just(new AlbumPermission(4, 102, 13, false, true));
    webTestClient.put().uri("/4")
            .body(mono, AlbumPermission.class)
            .exchange()
            .expectStatus().is2xxSuccessful();
  }

  @Test
  @Order(3)
  void findAllPermissions() {
    webTestClient.get()
            .exchange()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectStatus().isOk()
            .expectBodyList(AlbumPermission.class);
  }

  @Test
  void inserOneUser() {
    User user = new User();
    user.setId(1);
    user.setName("Prueba 1");
    user.setUsername("Probando");
    user.setEmail("example@example.com");
    user.setPhone("1111111");
    user.setWebsite("example.com");
    user.setAddress(user.new Address("calle 4", "35", "Medellín", "023697"));
    user.setCompany(user.new Company("contoso", "contoso", "Better software"));

    webTestClient.post()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(user), User.class)
            .exchange()
            .expectStatus().isCreated();
  }
}
