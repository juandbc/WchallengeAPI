package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Post;
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
public class PostWebRepository extends WebRepository implements PostRepository {

  private final WebClient webClient;

  public PostWebRepository(@Value("${webServiceRepository}") String baseUrl, @Value("${postsPath}") String path) {
    this.webClient = WebClient.create(baseUrl + path);
  }

  @Override
  public List<Post> findAll() {
    Flux<Post> result = (Flux<Post>) getFlux(webClient, "", Post.class);
    return result.collectList().block();
  }

  @Override
  public Post findById(int id) {
    Mono<Post> result = (Mono<Post>) getMono(webClient, String.format("/%s", id), Post.class);
    return result.block();
  }

  @Override
  public List<Post> findByUser(int userId) {
    String uriPath = String.format("?userId=%d", userId);
    Flux<Post> result = (Flux<Post>) getFlux(webClient, uriPath, Post.class);
    return result.collectList().block();
  }
}
