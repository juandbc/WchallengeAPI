package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Repository
public class UserWebRepository implements UserRepository {

  private final WebClient webClient;

  private static final String PATH = "/users";

  public UserWebRepository(@Value("${webServiceRepository}") String baseUrl) {
    this.webClient = WebClient.create(baseUrl);
  }

  @Override
  public List<User> findAll() {
    Flux<User> result = webClient.get().uri(PATH).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(User.class);
    return result.collectList().block();
  }

  @Override
  public User findById(int id) {
    Mono<User> result = webClient.get().uri(PATH + "/{id}", id)
            .retrieve()
            .bodyToMono(User.class);
    return result.block();
  }
}
