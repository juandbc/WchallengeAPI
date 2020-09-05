package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Post;
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
public class PostWebRepository implements PostRepository {

  private final WebClient webClient;

  private static final String PATH = "/posts";

  public PostWebRepository(@Value("${webServiceRepository}") String baseUrl) {
    this.webClient = WebClient.create(baseUrl);
  }

  @Override
  public List<Post> findAll() {
    Flux<Post> result = webClient.get().uri(PATH).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(Post.class);
    return result.collectList().block();
  }

  @Override
  public Post findById(int id) {
    Mono<Post> result = webClient.get().uri(PATH + "/{id}", id)
            .retrieve()
            .bodyToMono(Post.class);
    return result.block();
  }

  @Override
  public List<Post> findByUser(int userId) {
    String uriPath = String.format("%s?userId=%d", PATH, userId);
    Flux<Post> result = webClient.get().uri(uriPath).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(Post.class);
    return result.collectList().block();
  }
}
