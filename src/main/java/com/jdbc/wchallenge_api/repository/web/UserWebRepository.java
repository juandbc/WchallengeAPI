package com.jdbc.wchallenge_api.repository.web;

import com.jdbc.wchallenge_api.model.User;
import org.springframework.beans.factory.annotation.Value;
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
public class UserWebRepository implements WebRepository<User> {

  private final WebClient webClient;

  public UserWebRepository(@Value("${webServiceRepository}") String baseUrl, @Value("${usersPath}") String path) {
    this.webClient = WebClient.create(baseUrl + path);
  }

  @Override
  public List<User> findAll() {
    Flux<User> result = getFlux(webClient, "", User.class);
    return result.collectList().block();
  }

  @Override
  public User findById(int id) {
    Mono<User> result = getMono(webClient, String.format("/%s", id), User.class);
    return result.block();
  }
}
